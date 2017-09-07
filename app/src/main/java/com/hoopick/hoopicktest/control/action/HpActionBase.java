package com.hoopick.hoopicktest.control.action;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.os.Handler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoopick.hoopicktest.control.bluetooth.HpBluetoothManager;
import com.hoopick.hoopicktest.control.bluetooth.HpBluetoothSerialService;
import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.util.HpTimeUtil;
import com.hoopick.hoopicktest.data.HpDataManager;
import com.hoopick.hoopicktest.data.model.HpState;
import com.hoopick.hoopicktest.data.model.HpStateBluetooth;

/**
 * Created by junhyeok on 2017. 8. 16..
 */

public abstract class HpActionBase {

    protected Context mContext = null;
    protected HpState mPrevState = null;

    public HpActionBase(Context acontext){
        mContext = acontext;
    }

    protected abstract void applyState(HpState aState);
    protected abstract boolean preTask() throws Exception;
    protected abstract void postTask() throws Exception;

    public synchronized void execute() throws Exception {

        HpState lNewState = null;
        mPrevState = HpDataManager.get().getRepository().getLastState();
        if(null == mPrevState){
            lNewState = new HpState();
        }
        else{
            try{
                lNewState = mPrevState.clone();
            }
            catch (Exception e){
                lNewState = new HpState();
                e.printStackTrace();
            }
        }

        //
        if(false == preTask()){
            return;
        }

        //current Date
        lNewState.setGameDate(HpTimeUtil.getCurrentTime("yyyy-MM-dd"));
        //current Time
        lNewState.setCurrentTime(HpTimeUtil.getCurrentTime("HH:mm:ss"));
        //
        lNewState.setRemainingTime(HpGameManager.get().getTimer().remainGameClockStr());
        //
        lNewState.setElapsedTimeSec(String.format("%d",HpGameManager.get().getTimer().elapsedGameSec()));
        // elapsed sec
        lNewState.setElapsedTime(HpGameManager.get().getTimer().elapsedGameSecStr());

        // score
        lNewState.setScoreHome(String.format("%02d",HpGameManager.get().getTeamHome().getScore()));
        lNewState.setScoreAway(String.format("%02d",HpGameManager.get().getTeamAway().getScore()));

        // fouls
        lNewState.setFoulsHome(String.format("%02d",HpGameManager.get().getTeamHome().getFouls()));
        lNewState.setFoulsAway(String.format("%02d",HpGameManager.get().getTeamAway().getFouls()));

        //
        int lPlayLenthSec = playLenthSec(lNewState);
        lNewState.setPlayLengthSec(String.format("%d",lPlayLenthSec));

        //
        lNewState.setPlayLength(String.format("%02d:%02d",lPlayLenthSec/60,lPlayLenthSec%60));

        // incrementId
        int lPrevPlayid = Integer.parseInt(lNewState.getPlayId());
        lNewState.setPlayId(String.format("%d",++lPlayLenthSec));

        //
        applyState(lNewState);

        ObjectMapper lMapper = new ObjectMapper();
        String lJsonState = lMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lNewState);

        //log
        Log.i("Hoopick",lJsonState);

        //
        // Bluetooth Write
        if(HpBluetoothManager.get().getSerialService().getState() == HpBluetoothSerialService.STATE_CONNECTED) {

            HpStateBluetooth lStateBluetooth = new HpStateBluetooth(lNewState);
            final String lJsonStatBluetooth = lMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lStateBluetooth);

            Log.i("Hoopick",lJsonStatBluetooth);

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        HpBluetoothManager.get().write(lJsonStatBluetooth);
                    }
                    catch (Exception e){
                        Log.i("Hoopick",e.getMessage());
                    }
                }
            });

        }

        //
        HpDataManager.get().getRepository().addState(lNewState);
        HpDataManager.get().getRepository().save(mContext);
        postTask();

        runOnListener(new Runnable() {
            @Override
            public void run() {
                HpGameManager.get().getGameEventListener().onPostAction();
            }
        });

    }



    public int playLenthSec(HpState aPrevState) {
        if (null == aPrevState) {
            return 0;
        }

        int lPrevElapsedSec = Integer.parseInt(aPrevState.getElapsedTimeSec());
        int lPlayLengthSec = HpGameManager.get().getTimer().elapsedGameSec() - lPrevElapsedSec;

        return lPlayLengthSec;
    }
    protected void runOnListener(final Runnable aRunnable) {

        ((Activity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aRunnable.run();
            }
        });

    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public HpState getmPrevState() {
        return mPrevState;
    }

    public void setmPrevState(HpState mPrevState) {
        this.mPrevState = mPrevState;
    }
}

