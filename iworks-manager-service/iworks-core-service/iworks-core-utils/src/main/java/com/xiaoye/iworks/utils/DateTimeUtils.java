package com.xiaoye.iworks.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author yehl
 * @date 2018年6月23日
 */
public class DateTimeUtils {

	public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss SSSS";
	
	/**
     * 获取当前时间<br>
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String currentDateString() {
        return currentDateString(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前时间<br>
     * @param pattern 时间格式
     * @return
     */
    public static String currentDateString(String pattern) {
        return formatDateToString(new Date(), pattern);
    }
    
    /**
     * 获取当前时间<br>
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date currentDate() {
        return currentDate(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前时间<br>
     * @param pattern 时间格式
     * @return
     */
    public static Date currentDate(String pattern) {
        return formatDateToDate(new Date(), pattern);
    }

    /**
     * 格式化时间<br>
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @param date 时间
     * @return
     */
    public static String formatDateToString(Date date) {
        return formatDateToString(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化时间
     * @param date 时间
     * @param pattern 时间格式
     * @return
     */
    public static String formatDateToString(Date date, String pattern) {
        if(date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    
    /**
     * 格式化时间<br>
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @param date 时间
     * @return
     */
    public static Date formatDateToDate(Date date) {
        return formatDateToDate(date);
    }

    /**
     * 格式化时间
     * @param date 时间
     * @param pattern 时间格式
     * @return
     */
    public static Date formatDateToDate(Date date, String pattern) {
        try {
            if(date == null) {
                return date;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(formatDateToString(date));
        }
        catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 字符串转为时间<br>
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     */
    public static Date formatStringToDate(String dateStr) {
        return formatStringToDate(dateStr, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 字符串转为时间
     * @param dateStr 
     * @param pattern
     * @return
     */
    public static Date formatStringToDate(String dateStr, String pattern) {
		if (null == dateStr || "".equals(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(dateStr);
        }
        catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 时间字符串格式化
     * @param dateStr 
     * @param pattern
     * @return
     */
    public static String formatStringToDateString(String dateStr) {
		return formatStringToDateString(dateStr, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间字符串格式化
     * @param dateStr 
     * @param pattern
     * @return
     */
    public static String formatStringToDateString(String dateStr, String pattern) {
        if(dateStr == null) {
            return dateStr;
        }
        return formatDateToString(formatStringToDate(dateStr, pattern), pattern);
    }

    /**
     * 获取所取时间当天的零点时刻
     * @param date
     * @return
     */
    public static Date getZeroDateTime(Date date) {
        if(null == date){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);;       
        return calendar.getTime();
    }

    /**
     * 获取所取时间当天的零点时刻
     * @param dateStr
     * @return
     */
    public static Date getZeroDateTime(String dateStr){
        Date date = formatStringToDate(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND , 0);
        return calendar.getTime();
    }

    /**
     * 获取 {@link hour} 小时以后的时间(负数表示之前的时间)
    *
    * @param date
    * @param hour
    * @return
     */
    public static Date getDateTimeAfterHours(Date date, int hour){
        if(null == date){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.HOUR_OF_DAY, currentHour + hour);
        return calendar.getTime();
    }
    
    /**
     * 获取 {@link days} 天之后的时间(负数表示之前的时间)
     * @param date
     * @param days
     * @return
     */
	public static Date getDateTimeAfterDays(Date date, int days) {
        if(null == date){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);;
        return calendar.getTime();
    }

	/**
	 * 时间比较
	 * 
	 * @param date1
	 * @param operation {@link "<"、"<="、"=="、">"、">="}
	 * @param date2
	 * @return
	 */
	public static boolean compare(Date date1, String operation, Date date2) {
		switch (operation) {
		case "<":
			return date1.getTime() - date2.getTime() < 0;
		case "<=":
			return date1.getTime() - date2.getTime() <= 0;
		case "!=":
			return date1.getTime() - date2.getTime() != 0;
		case "==":
			return date1.getTime() - date2.getTime() == 0;
		case ">":
			return date1.getTime() - date2.getTime() > 0;
		case ">=":
			return date1.getTime() - date2.getTime() >= 0;

		default:
			break;
		}
		return false;
	}
}