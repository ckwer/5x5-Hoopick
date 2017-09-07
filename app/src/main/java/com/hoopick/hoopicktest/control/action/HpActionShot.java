package com.hoopick.hoopicktest.control.action;

import android.content.Context;
import android.util.Log;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;
import com.hoopick.hoopicktest.data.HpDataManager;
import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class HpActionShot extends HpActionBase {

    public static final String SHOT_MADE = "made";
    public static final String SHOT_MISSED = "missed";

    private String mEventType = "";
    private String mMadeOrMissed = "";
    private String mTeamExecuteEvent = "";
    private String mPlayerExecuteEvent = "";
    private String mPlayerAssist = "";
    private int mPointScore = 0;
    private int mAxisX = 0;
    private int mAxisY = 0;
    private int mDistance = 0;

    public HpActionShot(Context aContext, String aMadeOrMissed, int aPointScore, int aAxisX, int aAxisY, int aDistance) {
        super(aContext);

        mMadeOrMissed = aMadeOrMissed;
        mPointScore = aPointScore;
        mAxisX = aAxisX;
        mAxisY = aAxisY;
        mDistance = aDistance;
    }

    @Override
    protected boolean preTask() throws Exception {

        if (false == HpDataManager.get().getRepository().existByEventType(HpState.EVENT_TYPE_START_OF_PERIOD)) {
            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard("경기를 시작해 주세요.");
                }
            });
            return false;
        }


        HpPlayer lOldTeamGrabbedTheBall = HpGameManager.get().getBall().getGrabbedPlayer();

        if ((null == lOldTeamGrabbedTheBall) || lOldTeamGrabbedTheBall.getName().equalsIgnoreCase("")) {
            Log.e("", "공을 아무도 가지지 않았는데 슛을 했네");
            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard("공을 가진 사람이 없습니다.");
                }
            });

            return false;
        }

        mPlayerExecuteEvent = lOldTeamGrabbedTheBall.getName();
        mTeamExecuteEvent = lOldTeamGrabbedTheBall.getParentTeam().getName();

        // 득점
        if (mMadeOrMissed == SHOT_MADE) {
            // Made

            mEventType = HpState.EVENT_TYPE_SHOT;

            // 공격시간 멈춤
            HpGameManager.get().getTimer().getStopWatchShot().stop();

            HpGameManager.get().getBall().getGrabbedPlayer().getParentTeam().incScore(mPointScore);

            final int lHomeScore = HpGameManager.get().getTeamHome().getScore();
            final int lAwayScore = HpGameManager.get().getTeamAway().getScore();

            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onScoreResult(lHomeScore, lAwayScore);
                }
            });

            // assist
            HpState lPrevState = HpDataManager.get().getRepository().getLastState();
            if ( (null != lPrevState) && (lPrevState.getEventType() == HpState.EVENT_TYPE_PASS)) {
                int lPlayLenghSec = playLenthSec(lPrevState);
                if (lPlayLenghSec <= 1) {
                    mPlayerAssist = lPrevState.getteamExecutedEvent();
                }
            }

            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("Made %d, points: %s, assist: %s"
                            , mPointScore, mPlayerExecuteEvent, mPlayerAssist));
                }
            });

        }
        else {
            // Missed
            mEventType = HpState.EVENT_TYPE_SHOT;

            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("Missed: %s", mPlayerExecuteEvent));
                }
            });

        }

        runOnListener(new Runnable() {
            @Override
            public void run() {
                HpGameManager.get().getGameEventListener().onShotResult(mMadeOrMissed, mPointScore, mPlayerExecuteEvent, mPlayerAssist);
            }
        });


        return true;
    }

    @Override
    protected void postTask() throws Exception {

        // 공격시간 멈춤
        HpGameManager.get().getTimer().getStopWatchShot().stop();

        HpGameManager.get().getBall().setGrabbedPlayer("");

        runOnListener(new Runnable() {
            @Override
            public void run() {
                HpGameManager.get().getGameEventListener().onGrabbedTheBallResult(null);
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
        aState.setPointsScoredWithinEvent(Integer.toString(mPointScore));
        aState.setPlayerGrabbedTheBallAfterEvent("");
        aState.setMoreDetailOfEvent("");
        aState.setShotMadeOrMissed(mMadeOrMissed);
        aState.setteamSteal("");
        aState.setEventDetail("");
        aState.setShotDistance(Integer.toString(mDistance));
        aState.setShotAxisX(Integer.toString(mAxisX));
        aState.setShotAxisY(Integer.toString(mAxisY));
        aState.setEventDesc("");

    }

}
