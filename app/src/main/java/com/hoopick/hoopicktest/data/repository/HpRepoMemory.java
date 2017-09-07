package com.hoopick.hoopicktest.data.repository;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoopick.hoopicktest.data.repository.HpRepoInterface;
import com.hoopick.hoopicktest.control.game.HpGameManager;
import com.hoopick.hoopicktest.data.model.HpState;
import com.hoopick.hoopicktest.data.util.HpFileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 2016-09-24.
 */
public class HpRepoMemory implements HpRepoInterface {

    public static final String DIR_NAME = "Hoopick";

    // -------------------------------------------------------------
    // property

    private List<HpState> mStateList = new ArrayList<HpState>();


    // -------------------------------------------------------------
    // method

    @Override
    public HpState getLastState() {

        if (mStateList.isEmpty()) {
            return null;
        }

        return mStateList.get(mStateList.size()-1);
    }

    @Override
    public List<HpState> getStateList() {
        return mStateList;
    }

    @Override
    public void addState(HpState aHpState) {
        mStateList.add(aHpState);
    }

    @Override
    public boolean existByEventType(String aEventType) {

        for (HpState nState : mStateList) {
            if (nState.getEventType().equalsIgnoreCase(aEventType)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        mStateList.clear();
    }

    @Override
    public void save(Context aContext) throws Exception {

        String lSaveFileName = HpGameManager.get().getSaveFileName();

        try {

            ObjectMapper lMapper = new ObjectMapper();

            // String lJsonStateList = lMapper.writeValueAsString(mStateList);
            // Log.i("HpRepoMemory", lJsonStateList);

            if (HpFileUtil.isExternalStorageWritable()) {

                File lDir = HpFileUtil.getExternalPublicDownload(aContext, DIR_NAME);
                File lFile = new File(lDir, lSaveFileName);

                // Files.write(lJsonStateList, lFile, Charset.forName("UTF-8"));
                lMapper.writeValue(lFile, mStateList);

            }
            else {
                Log.e("", "No External Storage");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }


    // -------------------------------------------------------------
    // setter/getter

    public List<HpState> getSateList() {
        return mStateList;
    }


}
