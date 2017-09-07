package com.hoopick.hoopicktest.control.game.team;

import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayer;
import com.hoopick.hoopicktest.control.game.team.player.HpPlayerRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 2016-09-23.
 */
public class HpTeam {

    public static final int TEAM_TYPE_NONE = -1;
    public static final int TEAM_TYPE_HOME = 0;
    public static final int TEAM_TYPE_AWAY = 1;

    public HpTeam(int aTeamType) {
        mTeamType = aTeamType;
    }

    private int mTeamType = TEAM_TYPE_NONE;
    private String mName = "";
    private List<HpPlayer> mPlayerList = new ArrayList<HpPlayer>();
    private int mScore = 0;
    private int mFouls = 0;

    public HpTeam getEnemyTeam() {

        HpTeam lEnemyTeam = null;

        if (mTeamType == TEAM_TYPE_HOME) {
            lEnemyTeam = HpGameManager.get().getTeamAway();
        }
        else {
            lEnemyTeam = HpGameManager.get().getTeamHome();
        }

        return lEnemyTeam;
    }

    public void each(HpPlayerRunnable aPlayerRun) {
        for (HpPlayer nPlayer : mPlayerList) {
            aPlayerRun.run(nPlayer);
        }
    }

    public void clearPlayer() {
        mPlayerList.clear();
    }

    public void addPlayer(HpPlayer aPlayer) {

        aPlayer.setParentTeam(this);
        mPlayerList.add(aPlayer);

    }

    public HpPlayer findPlayerBySlot(int aSlot) {

        for (HpPlayer nPlayer : mPlayerList) {
            if (nPlayer.getSlot() == aSlot) {
                return nPlayer;
            }
        }

        return null;

    }

    public HpPlayer findPlayerByName(String aName) {

        for (HpPlayer nPlayer : mPlayerList) {
            if (nPlayer.getName().trim().equalsIgnoreCase(aName.trim())) {
                return nPlayer;
            }
        }

        return null;
    }

    public HpPlayer findPlayerByNumber(String aNumber) {

        for (HpPlayer nPlayer : mPlayerList) {
            if (nPlayer.getNumber().trim().equalsIgnoreCase(aNumber.trim())) {
                return nPlayer;
            }
        }

        return null;
    }


    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public List<HpPlayer> getPlayerList() {
        return mPlayerList;
    }

    public Integer getScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public void incScore(int aPoint) {
        mScore += aPoint;
    }

    public int getTeamType() {
        return mTeamType;
    }

    public void setTeamType(int mTeamType) {
        this.mTeamType = mTeamType;
    }

    public void incFouls() {
        this.mFouls += 1;
    }

    public Integer getFouls() {
        return mFouls;
    }

    public void setFouls(int mFouls) {
        this.mFouls = mFouls;
    }
}
