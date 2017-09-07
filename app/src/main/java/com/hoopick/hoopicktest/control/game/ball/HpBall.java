package com.hoopick.hoopicktest.control.game.ball;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;

/**
 * Created by pro on 2016-09-23.
 */
public class HpBall {

    public HpBall() {
    }

    private HpGameManager mGameManager = null;
    private HpPlayer mGrabbedPlayer = null;
    private HpPlayer mLastGrabbedPlayer = null;

    public void setGameManager(HpGameManager aGameManager) {
        mGameManager = aGameManager;
    }

    public HpPlayer getGrabbedPlayer() {
        return mGrabbedPlayer;
    }

    public HpPlayer setGrabbedPlayer(HpPlayer aGrabbedPlayer) {
        this.mGrabbedPlayer = aGrabbedPlayer;

        // 마지막 공 잡은 사람
        if (null != mGrabbedPlayer) {
            mLastGrabbedPlayer = mGrabbedPlayer;
        }

        return mLastGrabbedPlayer;
    }

    public HpPlayer getLastGrabbedPlayer() {
        return mLastGrabbedPlayer;
    }

    public HpPlayer setGrabbedPlayer(String aPlayerName) throws Exception {

        HpPlayer lPlayer = null;

        if (aPlayerName.equalsIgnoreCase("")) {
            lPlayer = null;
        }
        else {
            lPlayer = findPlayerByName(aPlayerName);
        }

        return setGrabbedPlayer(lPlayer);
    }


    public HpPlayer findPlayerByName(String aName) {

        HpPlayer lPlayerFound = null;

        lPlayerFound = mGameManager.getTeamHome().findPlayerByName(aName);
        if (null == lPlayerFound) {
            lPlayerFound = mGameManager.getTeamAway().findPlayerByName(aName);
        }

        return lPlayerFound;
    }

}
