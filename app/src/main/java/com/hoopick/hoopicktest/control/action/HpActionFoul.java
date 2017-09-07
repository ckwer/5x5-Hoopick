package com.hoopick.hoopicktest.control.action;

import android.content.Context;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;
import com.hoopick.hoopicktest.data.HpDataManager;
import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class HpActionFoul extends HpActionBase {

    private HpPlayer mPlayFoul = null;
    private String mTeamExecuteEvent = "";
    private String mPlayExecuteEvent ="";
    private String mEventDetail ="";

    public HpActionFoul(Context aContext, final String aFoulPlayTeam) {
        super(aContext);

        mPlayFoul = HpGameManager.get().findPlayByTeam(aFoulPlayTeam);

        if(null == mPlayFoul){
            return;
        }

        mTeamExecuteEvent = mPlayFoul.getParentTeam().getName();
        mPlayExecuteEvent = mPlayFoul.getName();

        HpPlayer lPlayerGrabbedBall = HpGameManager.get().getBall().getLastGrabbedPlayer();
        if((null != lPlayerGrabbedBall) && (lPlayerGrabbedBall.getParentTeam().getTeamType() == mPlayFoul.getParentTeam().getTeamType()) ){
            mEventDetail = HpState.EVENT_DETAIL_FOUL_OFFENSIVE;
        }
        else {
            mEventDetail = HpState.EVENT_DETAIL_FOUL_DEFENSIVE;
        }

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

        if (null == mPlayFoul) {
            runOnListener(new Runnable() {
                @Override
                public void run() {
                    HpGameManager.get().getGameEventListener().onDisplayBoard("Foul 선수가 없습니다.");
                }
            });
            return false;
        }

//        HpPlayer lPlayer = HpGameManager.get().getBall().getLastGrabbedPlayer();
//
//        if (null == lPlayer) {
//            runOnListener(new Runnable() {
//                @Override
//                public void run() {
//                    HpGameManager.get().getGameEventListener().onDisplayBoard("공을 가진 사람이 없습니다.");
//                }
//            });
//
//            return false;
//        }

        mPlayFoul.getParentTeam().incFouls();


        return true;
    }
    @Override
    protected void postTask() throws Exception {

        runOnListener(new Runnable() {
            @Override
            public void run() {

                int lFoulsHome = HpGameManager.get().getTeamHome().getFouls();
                int lFoulsAway = HpGameManager.get().getTeamAway().getFouls();

                HpGameManager.get().getGameEventListener().onFoulsResult(mPlayFoul, lFoulsHome, lFoulsAway);
            }
        });

        runOnListener(new Runnable() {
            @Override
            public void run() {
                HpGameManager.get().getGameEventListener().onDisplayBoard(String.format("Foul : '%s' %s", mPlayExecuteEvent, mEventDetail));
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
        aState.setEventType(HpState.EVENT_TYPE_FOUL);

        aState.setJumpBallAway("");
        aState.setJumpBallHome("");
        aState.setteamBlockedAShot("");
        aState.setteamCheckIn("");
        aState.setteamCheckOut("");
        aState.setFreeThrowsOrder("");
        aState.setteamFoul(mPlayExecuteEvent);
        aState.setteamWhistleGrabbed("");
        aState.setFreeThrowsCount("");
        aState.setteamExecutedEvent(mPlayExecuteEvent);
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
