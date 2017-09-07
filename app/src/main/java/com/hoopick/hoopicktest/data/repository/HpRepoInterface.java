package com.hoopick.hoopicktest.data.repository;

import android.content.Context;

import com.hoopick.hoopicktest.data.model.HpState;

import java.util.List;

/**
 * Created by junhyeok on 2017. 8. 16..
 */

public interface HpRepoInterface {

    void clear();

    HpState getLastState();

    List<HpState> getStateList();

    void addState(HpState aHpState);

    boolean existByEventType(String aEventType);

    void save(Context aContext) throws Exception;
}
