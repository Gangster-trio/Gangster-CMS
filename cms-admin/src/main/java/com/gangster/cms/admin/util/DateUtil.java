package com.gangster.cms.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String formatDateToString(Date date, String dtFormat) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateToString(date, dateFormat);
    }
}
