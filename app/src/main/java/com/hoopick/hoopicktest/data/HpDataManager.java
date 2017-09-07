package com.hoopick.hoopicktest.data;

import com.hoopick.hoopicktest.data.repository.HpRepoInterface;
import com.hoopick.hoopicktest.data.repository.HpRepoMemory;

/**
 * Created by pro on 2016-09-24.
 */
public class HpDataManager {

    public static final int REPO_TYPE_MEMORY = 1;

    // -------------------------------------------------------------
    // property

    private HpRepoInterface mRepository = new HpRepoMemory();



    // -------------------------------------------------------------
    // method



    // -------------------------------------------------------------
    // start singleton

    private static HpDataManager gHpDataManager;

    public static HpDataManager get() {
        if (null == gHpDataManager) {
            gHpDataManager = new HpDataManager();
        }
        return gHpDataManager;
    }

    private HpDataManager() {
    }


    // -------------------------------------------------------------
    // setter/getter

    public HpRepoInterface getRepository() {
        return mRepository;
    }

}
