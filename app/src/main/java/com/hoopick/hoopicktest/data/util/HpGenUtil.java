package com.hoopick.hoopicktest.data.util;

import com.hoopick.hoopicktest.control.util.HpTimeUtil;

/**
 * Created by pro on 2016-09-25.
 */
public class HpGenUtil {

    public static String genGameId() {

        String lTimeStr = HpTimeUtil.getCurrentTime("yyyyMMddHHmmss");
        return String.format("%s%04d", lTimeStr, (int)(Math.random()*1000));

    }

    public static String genFileName(String aTeamHome, String aTeamAway) {

        String lTimeStr = HpTimeUtil.getCurrentTime("yyyy_MMdd_HHmm_ss");
        return String.format("%s_%s_%s.txt", lTimeStr, aTeamHome, aTeamAway);

    }

}
