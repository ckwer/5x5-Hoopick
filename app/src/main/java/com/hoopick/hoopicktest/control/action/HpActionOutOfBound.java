package com.hoopick.hoopicktest.control.action;

import android.content.Context;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;
import com.hoopick.hoopicktest.data.HpDataManager;
import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class HpActionOutOfBound extends HpActionBase {


    private String mEventType = "";
    private String mEventDetail = "";
    private HpPlayer mPlayerTouch = null;
    private String mTeamExecuteEvent = "";
    private String mPlayerExecuteEvent = "";


    public HpActionOutOfBound(Context aContext, String aPlayerTouch) {
        super(aContext);

        mEventType = HpState.EVENT_TYPE_OUT_OF_BOUND;

        mPlayerTouch = HpGameManager.get().findPlayByTeam(aPlayerTouch);
        if (null == mPlayerExecuteEvent) {
            return;
        }

        HpPlayer lPlayerLastGrabbed = HpGameManager.get().getBall().getLastGrabbedPlayer();
        if (null != lPlayerLastGrabbed) {
            if (mPlayerTouch.getName().equalsIgnoreCase(lPlayerLastGrabbed.getName())) {
                mEventType = HpState.EVENT_TYPE_TURNOVER;
                mEventDetail = HpState.EVENT_DETAIL_BAD_PASS;
            }
        }

        mTeamExecuteEvent = mPlayerTouch.getParentTeam().getName();
        mPlayerExecuteEvent = mPlayerTouch.getName();

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

        if (null == mTeamExecuteEvent) {
            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("공잡은 선수가 없습니다."));
                }
            });

            return false;
        }


        return true;
    }

    @Override
    protected void postTask() throws Exception {

        runOnListener(new Runnable() {
            @Override
            public void run() {
                HpGameManager.get().getGameEventListener().onOutOfBoundResult(mPlayerTouch);
                HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("%s: %s", mEventType, mPlayerExecuteEvent));
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
//        aState.setRemainingTime(aRemainingTime);
//        aState.setElapsedTime(String.format("%02d:%02d", 0, 0));
//        aState.setElapsedTimeSec(""));
//        aState.setPlayLength(String.format("%02d:%02d", 0, 0));
//        aState.setPlayLengthSec("0");
//        aState.setPlayId("0");
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
        aState.setPlayerGrabbedTheBallAfterEvent("");
        aState.setMoreDetailOfEvent("");
        aState.setShotMadeOrMissed("");
        aState.setteamSteal("");
        aState.setEventDetail(mEventDetail);
        aState.setShotDistance("");
        aState.setShotAxisX("");
        aState.setShotAxisY("");
        aState.setEventDesc("");

    }

}

