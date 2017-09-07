package com.hoopick.hoopicktest.control.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 2016-09-21.
 */
public class HpTimeUtil {

    private static String defaultFormatString = "yyyyMMddHHmmss";
    private static DateFormat defaultFormatter = new SimpleDateFormat(defaultFormatString);
    private static Map<String, DateFormat> formatters = new HashMap<String, DateFormat>();


    public static String getCurrentTime(String aFormat) {
        try {
            return getString(getFormatter(aFormat).format(new Date()));
        } catch (Exception e) {
            return "";
        }
    }

    private static String getString(String val) {
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    private static DateFormat getFormatter(String format) {
        DateFormat formatter = formatters.get(format);
        if (formatter == null) {
            formatter = new SimpleDateFormat(format);
            formatters.put(format, new SimpleDateFormat(format));
        }
        return formatter;
    }

}
