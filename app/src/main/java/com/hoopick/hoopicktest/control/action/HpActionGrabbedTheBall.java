package com.hoopick.hoopicktest.control.action;

import android.content.Context;
import android.util.Log;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;
import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class HpActionGrabbedTheBall extends HpActionBase {

    private String mEventType = "";
    private String mEventDetail = "";
    private String mTeamExecuteEvent = "";
    private String mPlayerExecuteEvent = "";
    private String mTeamNameGrabbedTheBall = "";
    private String mPlayerNameSteal = "";

    HpPlayer mOldTeamGrabbedTheBall = null;
    HpPlayer mNewTeamGrabbedTheBall = null;

    public HpActionGrabbedTheBall(Context aContext
            , String aTeamNameGrabbedTheBall
    ) {
        super(aContext);

        mTeamNameGrabbedTheBall = aTeamNameGrabbedTheBall;
    }

    @Override
    protected boolean preTask() throws Exception {

        mOldTeamGrabbedTheBall = HpGameManager.get().getBall().getGrabbedPlayer();
        mNewTeamGrabbedTheBall = HpGameManager.get().findPlayByTeam(mTeamNameGrabbedTheBall);


        if (null == mPrevState) {
            Log.e("", "처음부터 패스를 할수는 없어!!");
            return false;
        }

        // shot 했으면 본인이 리바운드 할 수 있다.
        if (mPrevState.getEventType() != HpState.EVENT_TYPE_SHOT) {

            if (mNewTeamGrabbedTheBall.equals(mOldTeamGrabbedTheBall)) {
                Log.e("", "공을 이미 가지고 있는 경우");
                return false;
            }

        }


        if ( mPrevState.getEventType() == HpState.EVENT_TYPE_LINEUP) {
            new HpActionStartOfPeriod(mContext).execute();
            new HpActionGrabbedTheBall(mContext, mTeamNameGrabbedTheBall).execute();
            return false;
        }

        // 공잡았으면 무조건 경기시간 흘러야 하는거 아닌감?
        if (HpGameManager.get().isPaused()) {
            HpGameManager.get().resume();
        }

        if ( (mPrevState.getEventType() == HpState.EVENT_TYPE_SHOT) && (mPrevState.getShotMadeOrMissed().equalsIgnoreCase(HpActionShot.SHOT_MISSED))
                || (mPrevState.getEventType() == HpState.EVENT_TYPE_FREE_THROW) && (mPrevState.getShotMadeOrMissed().equalsIgnoreCase(HpActionFreeThrow.FREE_THROW_MISS))
                ) {

            HpPlayer lPlayerMissed = HpGameManager.get().findPlayByTeam(mPrevState.getteamExecutedEvent());


            if (lPlayerMissed.getParentTeam() != mNewTeamGrabbedTheBall.getParentTeam()) {
                // 공수 바뀐 경우
                mEventDetail = HpState.EVENT_DETAIL_REBOUND_DEFENSIVE;
            }
            else {
                // 공수 안바뀐 경우
                mEventDetail = HpState.EVENT_DETAIL_REBOUND_OFFENSIVE;
            }

            HpGameManager.get().getTimer().getStopWatchShot().stop();
            HpGameManager.get().getTimer().getStopWatchShot().start();

            // rebound
            mEventType = HpState.EVENT_TYPE_REBOUND;
            mPlayerExecuteEvent = mNewTeamGrabbedTheBall.getName();
            mTeamExecuteEvent = mNewTeamGrabbedTheBall.getParentTeam().getName();
            mPlayerNameSteal = "";

            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("Rebound: %s", mPlayerExecuteEvent));
                }
            });

        }
        else if ( (mPrevState.getEventType() == HpState.EVENT_TYPE_WHISTLE)
                || (mPrevState.getEventType() == HpState.EVENT_TYPE_SUBSTITUTION)
                )  {

            HpPlayer lPlayerWhistleGrabbed = HpGameManager.get().findPlayByTeam(mPrevState.getteamWhistleGrabbed());
            if (null != lPlayerWhistleGrabbed) {

                if (lPlayerWhistleGrabbed.getParentTeam() != mNewTeamGrabbedTheBall.getParentTeam()) {
                    // 공수 바뀐 경우
                    // 공격시간 재새작
                    HpGameManager.get().getTimer().getStopWatchShot().stop();
                    HpGameManager.get().getTimer().getStopWatchShot().start();

                }
                else {
                    // 공수 안바뀐 경우
                    // GameTime Resume
                }

                mEventType = HpState.EVENT_TYPE_SET;
                mPlayerExecuteEvent = mNewTeamGrabbedTheBall.getName();
                mTeamExecuteEvent = mNewTeamGrabbedTheBall.getParentTeam().getName();
                mPlayerNameSteal = "";
            }

        }
        else if (mPrevState.getEventType() == HpState.EVENT_TYPE_OUT_OF_BOUND) {

            // 공격시간 재새작 하지 않는다. 수비수 손 맞고 나간 경우이므로
//            HpGameManager.get().getTimer().getStopWatchShot().stop();
//            HpGameManager.get().getTimer().getStopWatchShot().start();

            mEventType = HpState.EVENT_TYPE_SET;
            mPlayerExecuteEvent = mNewTeamGrabbedTheBall.getName();
            mTeamExecuteEvent = mNewTeamGrabbedTheBall.getParentTeam().getName();
            mPlayerNameSteal = "";

        }
        else if (mPrevState.getEventType() == HpState.EVENT_TYPE_FOUL) {

            // 공격시간 재시작

            HpGameManager.get().getTimer().getStopWatchShot().stop();
            HpGameManager.get().getTimer().getStopWatchShot().start();

            mEventType = HpState.EVENT_TYPE_SET;
            mPlayerExecuteEvent = mNewTeamGrabbedTheBall.getName();
            mTeamExecuteEvent = mNewTeamGrabbedTheBall.getParentTeam().getName();
            mPlayerNameSteal = "";

        }
        else {

            if ((null == mOldTeamGrabbedTheBall) || mOldTeamGrabbedTheBall.getName().equalsIgnoreCase("")) {
                mEventType = HpState.EVENT_TYPE_PASS;
                mPlayerExecuteEvent = "";
                mTeamExecuteEvent = "";
                mPlayerNameSteal = "";

                // 공격시간 재새작
                HpGameManager.get().getTimer().getStopWatchShot().stop();
                HpGameManager.get().getTimer().getStopWatchShot().start();
            }
            else {

                mPlayerExecuteEvent = mOldTeamGrabbedTheBall.getName();
                mTeamExecuteEvent = mOldTeamGrabbedTheBall.getParentTeam().getName();

                if (mOldTeamGrabbedTheBall.getParentTeam() == mNewTeamGrabbedTheBall.getParentTeam()) {
                    // Pass
                    mEventType = HpState.EVENT_TYPE_PASS;
                    mPlayerNameSteal = "";

                    runOnListener(new Runnable() {
                        @Override
                        public void run() {
                            HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("Pass: %s -> %s"
                                    , mOldTeamGrabbedTheBall.getName(), mNewTeamGrabbedTheBall.getName()));
                        }
                    });

                }
                else {
                    // TurnOver
                    mEventType = HpState.EVENT_TYPE_TURNOVER;
                    mEventDetail = HpState.EVENT_DETAIL_BAD_PASS;
                    mPlayerNameSteal = mTeamNameGrabbedTheBall;

                    // 공격시간 재새작
                    HpGameManager.get().getTimer().getStopWatchShot().stop();
                    HpGameManager.get().getTimer().getStopWatchShot().start();

                    runOnListener(new Runnable() {
                        @Override
                        public void run() {
                            HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("TurnOver: %s, Steal: %s"
                                    , mPlayerExecuteEvent, mPlayerNameSteal));
                        }
                    });

                }

            }

        }

        return true;
    }

    @Override
    protected void postTask() throws Exception {


        HpGameManager.get().getBall().setGrabbedPlayer(mNewTeamGrabbedTheBall);

        runOnListener(new Runnable() {
            @Override
            public void run() {
                HpGameManager.get().getGameEventListener().onGrabbedTheBallResult(mNewTeamGrabbedTheBall);
                HpGameManager.get().getGameEventListener().onReadyToGrabbedTheBall();
            }
        });
    }

    @Override
    protected void applyState(HpState aState) {

//        aState.setGameId(aGameId);
//        aState.setSeason(aSeason);
//        aState.setGameDate(aGameDate);
//        aState.setTeamHome(mTeamHome);
//        aState.setTeamHome(mTeamAway);
//        aState.setPlayerAwayName1(aPlayerAway1);
//        aState.setPlayerAwayName2(aPlayerAway2);
//        aState.setPlayerAwayName3(aPlayerAway3);
//        aState.setPlayerAwayName4(aPlayerAway4);
//        aState.setPlayerAwayName5(aPlayerAway5);
//        aState.setPlayerHomeName1(aPlayerHome1);
//        aState.setPlayerHomeName2(aPlayerHome2);
//        aState.setPlayerHomeName3(aPlayerHome3);
//        aState.setPlayerHomeName4(aPlayerHome4);
//        aState.setPlayerHomeName5(aPlayerHome5);
//        aState.setPeriod("1");
//        aState.setScoreAway("0");
//        aState.setScoreHome("0");
//        aState.setRemainingTime(String.format("%02d:%02d", 0, 0));
//        aState.setElapsedTime(String.format("%02d:%02d", 0, 0));
//        aState.setElapsedTimeSec(""));
//        aState.setPlayLength(String.format("%02d:%02d", 0, 0));
//        aState.setPlayLengthSec("0");
//        aState.setPlayId(String.format("%d", Integer.parseInt(aState.getPlayId().trim()) + 1));
        aState.setTeamNameExecutedEvent(mTeamExecuteEvent);
        aState.setEventType(mEventType);
        aState.setJumpBallAway("");
        aState.setJumpBallHome("");
        aState.setteamBlockedAShot("");
        aState.setteamCheckIn("");
        aState.setteamCheckOut("");
        aState.setFreeThrowsOrder("");
        aState.setteamFoul("");
        aState.setteamWhistleGrabbed("");
        aState.setFreeThrowsCount("");
        aState.setteamExecutedEvent(mPlayerExecuteEvent);
        aState.setPointsScoredWithinEvent("");
        aState.setPlayerGrabbedTheBallAfterEvent(mTeamNameGrabbedTheBall);
        aState.setMoreDetailOfEvent("");
        aState.setShotMadeOrMissed("");
        aState.setteamSteal(mPlayerNameSteal);
        aState.setEventDetail(mEventDetail);
        aState.setShotDistance("");
        aState.setShotAxisX("");
        aState.setShotAxisY("");
        aState.setEventDesc("");

    }


    public String getmTeamExecuteEvent() {
        return mTeamExecuteEvent;
    }

    public void setmTeamExecuteEvent(String mTeamExecuteEvent) {
        this.mTeamExecuteEvent = mTeamExecuteEvent;
    }

    public String getmPlayerExecuteEvent() {
        return mPlayerExecuteEvent;
    }

    public void setmPlayerExecuteEvent(String mPlayerExecuteEvent) {
        this.mPlayerExecuteEvent = mPlayerExecuteEvent;
    }

    public String getmPlayerNameGrabbedTheBall() {
        return mTeamNameGrabbedTheBall;
    }

    public void setmPlayerNameGrabbedTheBall(String mPlayerNameGrabbedTheBall) {
        this.mTeamNameGrabbedTheBall = mPlayerNameGrabbedTheBall;
    }
}
