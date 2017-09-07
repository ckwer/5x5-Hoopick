package com.hoopick.hoopicktest.control.action;

import android.content.Context;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.data.HpDataManager;
import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by pro on 2016-10-24.
 */
public class HpActionSubstitution extends HpActionBase {

    private String mPlayerHomeName1 = "";
    private String mPlayerHomeNumber1 = "";
    private String mPlayerHomeName2 = "";
    private String mPlayerHomeNumber2 = "";
    private String mPlayerHomeName3 = "";
    private String mPlayerHomeNumber3 = "";
    private String mPlayerHomeName4 = "";
    private String mPlayerHomeNumber4 = "";

    private String mPlayerAwayName1 = "";
    private String mPlayerAwayNumber1 = "";
    private String mPlayerAwayName2 = "";
    private String mPlayerAwayNumber2 = "";
    private String mPlayerAwayName3 = "";
    private String mPlayerAwayNumber3 = "";
    private String mPlayerAwayName4 = "";
    private String mPlayerAwayNumber4 = "";    

    public HpActionSubstitution(Context aContext,
                                String aPlayerHomeName1,
                                String aPlayerHomeNumber1,
                                String aPlayerHomeName2,
                                String aPlayerHomeNumber2,
                                String aPlayerHomeName3,
                                String aPlayerHomeNumber3,
                                String aPlayerHomeName4,
                                String aPlayerHomeNumber4,
                                String aPlayerAwayName1,
                                String aPlayerAwayNumber1,
                                String aPlayerAwayName2,
                                String aPlayerAwayNumber2,
                                String aPlayerAwayName3,
                                String aPlayerAwayNumber3,
                                String aPlayerAwayName4,
                                String aPlayerAwayNumber4
    ) {
        super(aContext);

        mPlayerHomeName1 = aPlayerHomeName1;
        mPlayerHomeNumber1 = aPlayerHomeNumber1;
        mPlayerHomeName2 = aPlayerHomeName2;
        mPlayerHomeNumber2 = aPlayerHomeNumber2;
        mPlayerHomeName3 = aPlayerHomeName3;
        mPlayerHomeNumber3 = aPlayerHomeNumber3;
        mPlayerHomeName4 = aPlayerHomeName4;
        mPlayerHomeNumber4 = aPlayerHomeNumber4;

        mPlayerAwayName1 = aPlayerAwayName1;
        mPlayerAwayNumber1 = aPlayerAwayNumber1;
        mPlayerAwayName2 = aPlayerAwayName2;
        mPlayerAwayNumber2 = aPlayerAwayNumber2;
        mPlayerAwayName3 = aPlayerAwayName3;
        mPlayerAwayNumber3 = aPlayerAwayNumber3;
        mPlayerAwayName4 = aPlayerAwayName4;
        mPlayerAwayNumber4 = aPlayerAwayNumber4;        

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

        return true;
    }

    @Override
    protected void postTask() throws Exception {

        HpGameManager.get().findPlayerBySlot(0, 0).setName(mPlayerHomeName1);
        HpGameManager.get().findPlayerBySlot(0, 0).setNumber(mPlayerHomeNumber1);

        HpGameManager.get().findPlayerBySlot(0, 1).setName(mPlayerHomeName2);
        HpGameManager.get().findPlayerBySlot(0, 1).setNumber(mPlayerHomeNumber2);

        HpGameManager.get().findPlayerBySlot(0, 2).setName(mPlayerHomeName3);
        HpGameManager.get().findPlayerBySlot(0, 2).setNumber(mPlayerHomeNumber3);

        HpGameManager.get().findPlayerBySlot(0, 3).setName(mPlayerHomeName4);
        HpGameManager.get().findPlayerBySlot(0, 3).setNumber(mPlayerHomeNumber4);


        HpGameManager.get().findPlayerBySlot(1, 0).setName(mPlayerAwayName1);
        HpGameManager.get().findPlayerBySlot(1, 0).setNumber(mPlayerAwayNumber1);

        HpGameManager.get().findPlayerBySlot(1, 1).setName(mPlayerAwayName2);
        HpGameManager.get().findPlayerBySlot(1, 1).setNumber(mPlayerAwayNumber2);

        HpGameManager.get().findPlayerBySlot(1, 2).setName(mPlayerAwayName3);
        HpGameManager.get().findPlayerBySlot(1, 2).setNumber(mPlayerAwayNumber3);

        HpGameManager.get().findPlayerBySlot(1, 3).setName(mPlayerAwayName4);
        HpGameManager.get().findPlayerBySlot(1, 3).setNumber(mPlayerAwayNumber4);
        
    }

    @Override
    protected void applyState(HpState aState) {

//        aState.setGameId(aGameId);
//        aState.setSeason(aSeason);
//        aState.setGameDate(aGameDate);
//        aState.setTeamHome(mTeamHome);
//        aState.setTeamHome(mTeamAway);
        aState.setPlayerHomeName1(mPlayerHomeName1);
        aState.setPlayerHomeName2(mPlayerHomeName2);
        aState.setPlayerHomeName3(mPlayerHomeName3);
        aState.setPlayerHomeName4(mPlayerHomeName4);
        aState.setPlayerHomeName5("");
        aState.setPlayerHomeNumber1(mPlayerHomeNumber1);
        aState.setPlayerHomeNumber2(mPlayerHomeNumber2);
        aState.setPlayerHomeNumber3(mPlayerHomeNumber3);
        aState.setPlayerHomeNumber4(mPlayerHomeNumber4);
        aState.setPlayerHomeNumber5("");
        aState.setPlayerAwayName1(mPlayerAwayName1);
        aState.setPlayerAwayName2(mPlayerAwayName2);
        aState.setPlayerAwayName3(mPlayerAwayName3);
        aState.setPlayerAwayName4(mPlayerAwayName4);
        aState.setPlayerAwayName5("");
        aState.setPlayerAwayNumber1(mPlayerAwayNumber1);
        aState.setPlayerAwayNumber2(mPlayerAwayNumber2);
        aState.setPlayerAwayNumber3(mPlayerAwayNumber3);
        aState.setPlayerAwayNumber4(mPlayerAwayNumber4);
        aState.setPlayerAwayNumber5("");
//        aState.setPeriod("1");
//        aState.setScoreAway("0");
//        aState.setScoreHome("0");
//        aState.setRemainingTime(aRemainingTime);
//        aState.setElapsedTime(String.format("%02d:%02d", 0, 0));
//        aState.setElapsedTimeSec(""));
//        aState.setPlayLength(String.format("%02d:%02d", 0, 0));
//        aState.setPlayLengthSec("0");
//        aState.setPlayId("0");
        aState.setTeamNameExecutedEvent("");
        aState.setEventType(HpState.EVENT_TYPE_SUBSTITUTION);
        aState.setJumpBallAway("");
        aState.setJumpBallHome("");
        aState.setteamBlockedAShot("");
        aState.setteamCheckIn("");
        aState.setteamCheckOut("");
        aState.setFreeThrowsOrder("");
        aState.setteamFoul("");
        aState.setteamWhistleGrabbed("");
        aState.setFreeThrowsCount("");
        aState.setteamExecutedEvent("");
        aState.setPointsScoredWithinEvent("");
        aState.setPlayerGrabbedTheBallAfterEvent("");
        aState.setMoreDetailOfEvent("");
        aState.setShotMadeOrMissed("");
        aState.setteamSteal("");
        aState.setEventDetail("");
        aState.setShotDistance("");
        aState.setShotAxisX("");
        aState.setShotAxisY("");
        aState.setEventDesc("");

    }

}
