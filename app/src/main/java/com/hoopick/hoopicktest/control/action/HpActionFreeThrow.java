package com.hoopick.hoopicktest.control.action;

import android.content.Context;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;
import com.hoopick.hoopicktest.data.HpDataManager;
import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class HpActionFreeThrow extends HpActionBase{

    public static final String FREE_THROW_MADE = "made";
    public static final String FREE_THROW_MISS = "missed";

    public static final String FREE_THROW_TYPE_TEAM_FOUL = "team foul";
    public static final String FREE_THROW_TYPE_SHOT_FOUL = "shot foul";

    private HpPlayer mPlayerFreeThrow = null;
    private String mMadeOrMissed = "";
    private String mTeamExecuteEvent = "";
    private String mPlayerExecuteEvent = "";
    private String mEventDetail = "";
    private String mPlayerFoul = "";
    private int mTeamFoul = 0;

    public HpActionFreeThrow(Context aContext, String aFreeThrowType, String aMadeOrMissed, String aPlayerFreeThrow, String aPlayerShotFoul, int aTeamFoul) {
        super(aContext);

        mPlayerFreeThrow = HpGameManager.get().findPlayByTeam(aPlayerFreeThrow);

        if (null == mPlayerFreeThrow) {
            return;
        }

        mMadeOrMissed = aMadeOrMissed;
        mPlayerExecuteEvent = mPlayerFreeThrow.getName();
        mTeamExecuteEvent = mPlayerFreeThrow.getParentTeam().getName();
        mEventDetail = aFreeThrowType;
        mPlayerFoul = aPlayerShotFoul;
        mTeamFoul = aTeamFoul;
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

        if (null == mPlayerFreeThrow) {
            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard("FreeThrow 선수가 없습니다.");
                }
            });
            return false;
        }


        if (mMadeOrMissed.equalsIgnoreCase(FREE_THROW_MADE)) {

            mPlayerFreeThrow.getParentTeam().incScore(1);

            final int lHomeScore = HpGameManager.get().getTeamHome().getScore();
            final int lAwayScore = HpGameManager.get().getTeamAway().getScore();

            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onScoreResult(lHomeScore, lAwayScore);
                }
            });

            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("FreeThrow: %s Made", mPlayerExecuteEvent));
                }
            });

        }
        else if (mMadeOrMissed.equalsIgnoreCase(FREE_THROW_MISS)) {

            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("FreeThrow: %s Miss", mPlayerExecuteEvent));
                }
            });

        }
        else {

        }

        return true;
    }


    @Override
    protected void postTask() throws Exception {

        HpGameManager.get().getBall().setGrabbedPlayer("");

        runOnListener(new Runnable() {
            @Override
            public void run() {

                HpGameManager.get().getGameEventListener().onGrabbedTheBallResult(null);

                HpGameManager.get().getGameEventListener().onFreeThrowResult(mPlayerFreeThrow, mMadeOrMissed, mTeamFoul);
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
        aState.setEventType(HpState.EVENT_TYPE_FREE_THROW);
        aState.setJumpBallAway("");
        aState.setJumpBallHome("");
        aState.setteamBlockedAShot("");
        aState.setteamCheckIn("");
        aState.setteamCheckOut("");
        aState.setFreeThrowsOrder("");
        aState.setteamFoul(mPlayerFoul);
        aState.setteamWhistleGrabbed("");
        aState.setFreeThrowsCount("");
        aState.setteamExecutedEvent(mPlayerExecuteEvent);
        aState.setPointsScoredWithinEvent("");
        aState.setPlayerGrabbedTheBallAfterEvent("");
        aState.setMoreDetailOfEvent("");
        aState.setShotMadeOrMissed(mMadeOrMissed);
        aState.setteamSteal("");
        aState.setEventDetail(mEventDetail);
        aState.setShotDistance("");
        aState.setShotAxisX("");
        aState.setShotAxisY("");
        aState.setEventDesc("");

    }
}
