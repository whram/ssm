package com.reacher.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转字符串
    public static String dateToString(Date date,String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        String format = sdf.format(date);
        return format;
    }

    //字符串转日期
    public static Date stringToDate(Date date, String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date parse = sdf.parse(str);
        return parse;
    }

}
