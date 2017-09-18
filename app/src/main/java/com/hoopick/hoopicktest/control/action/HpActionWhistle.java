package com.hoopick.hoopicktest.control.action;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.hoopick.hoopicktest.R;
import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;
import com.hoopick.hoopicktest.data.HpDataManager;
import com.hoopick.hoopicktest.data.model.HpState;

/**
 * Created by junhyeok on 2017. 8. 17..
 */

public class HpActionWhistle extends HpActionBase {

    public HpActionWhistle(Context aContext){
        super(aContext);
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


        // whistle sound
        MediaPlayer lPlayer = MediaPlayer.create(mContext, R.raw.buzzer);
        lPlayer.start();
        // GameTime Pause
        HpGameManager.get().pause();

        return true;
    }

    @Override
    protected void postTask() throws Exception {

        //
        HpGameManager.get().getBall().setGrabbedPlayer("");

        runOnListener(new Runnable() {
            @Override
            public void run() {
                HpGameManager.get().getGameEventListener().onGrabbedTheBallResult(null);
                HpGameManager.get().getGameEventListener().onWhistleResult();
                HpGameManager.get().getGameEventListener().onShotClockChicagoMusic_stop(); // Chicago 음악 멈춤
            }
        });


    }
    @Override
    protected void applyState(HpState aState) {

        String lPlayerWhistleGrabbed = "";

        HpPlayer lPlayerGrabbed = HpGameManager.get().getBall().getGrabbedPlayer();
        if (null == lPlayerGrabbed) {
            Log.e("Hoopick", String.format("whistle blowed. but no one has grabbed the ball."));
            lPlayerWhistleGrabbed = "";
        }
        else {
            lPlayerWhistleGrabbed = lPlayerGrabbed.getName();
        }

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
//        aState.setTeamNameExecutedEvent("");
        aState.setEventType(HpState.EVENT_TYPE_WHISTLE);

        aState.setJumpBallAway("");
        aState.setJumpBallHome("");
        aState.setteamBlockedAShot("");
        aState.setteamCheckIn("");
        aState.setteamCheckOut("");
        aState.setFreeThrowsOrder("");
        aState.setteamFoul("");
        aState.setteamWhistleGrabbed(lPlayerWhistleGrabbed);
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
