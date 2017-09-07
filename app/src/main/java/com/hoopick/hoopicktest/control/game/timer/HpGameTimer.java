package com.hoopick.hoopicktest.control.game.timer;

import com.hoopick.hoopicktest.control.util.StopWatch;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by pro on 2016-09-23.
 */
public class HpGameTimer {

    public static final int MAX_GAME_CLOCK_MIN = 10;    // min
    public static final int MAX_SHOT_CLOCK_SEC = 30;    // sec

    private HpGameTimerListener mGameTimerListener = null;
    public void setGameTimerListener(HpGameTimerListener aHpTimerListener) {
        mGameTimerListener = aHpTimerListener;
    }

    private StopWatch mStopWatchGame = new StopWatch();
    private StopWatch mStopWatchShot = new StopWatch();

    private Timer mTimerSec = new Timer();

    private int mMaxGameClockSec = MAX_GAME_CLOCK_MIN*60;
    private int mMaxShotClockSec = MAX_SHOT_CLOCK_SEC;

    public void startGame() {

        mStopWatchGame.start();
        mStopWatchShot.start();

        mTimerSec.schedule(mTimerTaskSec, 0, 1000);

    }

    public void setGameClockMinutes(int aGameClockMin, int aShotClockSec) {
        mMaxGameClockSec = aGameClockMin * 60;
        mMaxShotClockSec = aShotClockSec;
    }

    public void pause() {
        mStopWatchGame.pause();
        mStopWatchShot.pause();
    }

    public void resume() {
        mStopWatchGame.resume();
        mStopWatchShot.resume();
    }

    public void stop(){

        mStopWatchGame.stop();
        mStopWatchShot.stop();
    }

    public boolean isPaused() {
        return mStopWatchGame.isPaused();
    }

    public int elapsedGameSec() {
        return mStopWatchGame.elapsedSec();
    }

    public String elapsedGameSecStr() {
        return String.format("%02d:%02d", elapsedGameSec() /60, elapsedGameSec() %60);
    }

    public int elapsedShotSec() {
        return mStopWatchShot.elapsedSec();
    }

    public int remainGameClockSec() {
        int lRemainingGameClockSec = mMaxGameClockSec - elapsedGameSec();
        lRemainingGameClockSec = Math.max(lRemainingGameClockSec, 0);
        return lRemainingGameClockSec;
    }

    public String remainGameClockStr() {
        return String.format("%02d:%02d", remainGameClockSec() /60, remainGameClockSec() %60);
    }

    public int remainShotClockSec() {
        int lRemainingShotClockSec = mMaxShotClockSec - elapsedShotSec();
        lRemainingShotClockSec = Math.max(lRemainingShotClockSec, 0);
        return lRemainingShotClockSec;
    }

    public String remainShotClockStr() {
        return String.format("%02d", remainShotClockSec());
    }

    private TimerTask mTimerTaskSec = new TimerTask() {
        @Override
        public void run() {

            if (null != mGameTimerListener) {
                mGameTimerListener.onTick(remainGameClockSec(), remainShotClockSec());
            }

        }
    };

    public StopWatch getStopWatchGame() {
        return mStopWatchGame;
    }

    public void setStopWatchGame(StopWatch mStopWatchGame) {
        this.mStopWatchGame = mStopWatchGame;
    }

    public StopWatch getStopWatchShot() {
        return mStopWatchShot;
    }

    public void setStopWatchShot(StopWatch mStopWatchShot) {
        this.mStopWatchShot = mStopWatchShot;
    }

    public int getmMaxGameClockSec() {
        return mMaxGameClockSec;
    }

    public void setmMaxGameClockSec(int mMaxGameClockSec) {
        this.mMaxGameClockSec = mMaxGameClockSec;
    }

    public int getmMaxShotClockSec() {
        return mMaxShotClockSec;
    }

    public void setmMaxShotClockSec(int mMaxShotClockSec) {
        this.mMaxShotClockSec = mMaxShotClockSec;
    }
}
