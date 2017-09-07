package com.hoopick.hoopicktest.control.game.team.player;

import com.hoopick.hoopicktest.control.game.team.HpTeam;

/**
 * Created by pro on 2016-09-23.
 */
public class HpPlayer {

    public HpPlayer(int aSlot, String aName, String aNumber) {
        setSlot(aSlot);
        setName(aName);
        setNumber(aNumber);
    }
    private HpTeam mParentTeam = null;
    private int mSlot = 0;
    private String mName = "";
    private String mNumber = "";
    private Object mTag = null;


    public int getSlot() {
        return mSlot;
    }

    public void setSlot(int aSlot) {
        this.mSlot = aSlot;
    }

    public HpTeam getParentTeam() {
        return mParentTeam;
    }

    public void setParentTeam(HpTeam aParentTeam) {
        this.mParentTeam = aParentTeam;
    }

    public String getName() {
        return mName;
    }

    public void setName(String aName) {
        this.mName = aName;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String aNumber) {
        this.mNumber = aNumber;
    }

    public Object getTag() {
        return mTag;
    }

    public void setTag(Object aTag) {
        this.mTag = aTag;
    }

}
