package com.gangster.cms.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Yoke
 * Created on 2018/4/30
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String str = "2018-04-06 00:00:00 ~ 2018-04-07 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strings = str.split("~");
        System.out.println(strings[0].length());
        String startDate = strings[0].trim();
        System.out.println(startDate.length());
        simpleDateFormat.parse(startDate);
    }
}
