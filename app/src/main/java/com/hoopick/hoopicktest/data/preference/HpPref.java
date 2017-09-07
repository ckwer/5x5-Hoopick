package com.hoopick.hoopicktest.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pro on 2016-09-21.
 */
public class HpPref {

    public final static String PREF_NAME = "hoopick";

    // ---------------------------------------------------------------------------------
    // state
    public static final String STATE = "state";
    public static void setState(Context aContext, String aState) {
        SharedPreferences lPref = aContext.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor lEdit = lPref.edit();
        lEdit.putString(STATE, aState);
        lEdit.commit();
    }

    public static String getState(Context aContext) {
        SharedPreferences lPref = aContext.getSharedPreferences(PREF_NAME, 0);
        String lBrandName = lPref.getString(STATE, "");
        return lBrandName;
    }



}
