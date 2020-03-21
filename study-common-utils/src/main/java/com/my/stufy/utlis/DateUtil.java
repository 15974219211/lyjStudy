package com.my.stufy.utlis;


import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    public static String FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd日";
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    public static String FORMAT_MEDIUM_CN = "yyyy年MM月dd日 HH时mm分";

    public static String FORMAT_NOYEAR_CN = "MM月dd日 HH:mm";
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    private static String FORMAT_ERP = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_ERP_TIME = "HH:mm";

    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据格式返回当前日期
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }
    
    /**
     * 得到当天日期格式化字符串
     */
    public static final String getTodayStr() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(now);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    /**
     * 使用用户格式格式化日期
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 使用预设格式提取字符串日期
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }

    /**
     * 使用用户格式提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加活减少天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取年份
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 按默认格式的字符串距离今天的天数
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }


    public static boolean isToday(Date date) {
        Calendar c1 = Calendar.getInstance();              
        c1.setTime(date);                                 
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);   
        
        Calendar c2 = Calendar.getInstance();    
        c2.setTime(new Date());      
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH)+1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);   
        
        if(year1 == year2 && month1 == month2 && day1 == day2){
            return true;
        }
        return false;                               
    }         
    
    /**
     * 返回加或减后的日期
     */
    public static final Date add(Date date,int field,int amount) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        Date retDate = calendar.getTime();
        return retDate;
    }

    //添加指定分钟
    public static Calendar addMinutes(Date date, int minuteNum) {
        if(date == null){
            date = new Date();
        }
        Calendar c = Calendar.getInstance();              
        c.setTime(date);                                 
        c.add(Calendar.MINUTE, minuteNum);                    
        return c;                               
    }
    public static boolean getCompareDate(Date date1,int number){

    	 Calendar c1= Calendar.getInstance();

    	 Calendar c2= Calendar.getInstance();

    	 c1.setTime(date1);
    	 if(number!=0){
    		 c1.add(Calendar.HOUR,number);
    	 }
    	 int result=c1.compareTo(c2);

    	 if(result==0){
    	 return true;

    	 }else if(result<0){
    		 return false;
    	 }else{
    		 return true;
    	 }
    }

    //比较字符日期的大小
    public static int compare(String s1,String s2){
    	java.text.DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Calendar c1= Calendar.getInstance();
    	Calendar c2= Calendar.getInstance();
    	try
    	{
    	c1.setTime(df.parse(s1));
    	c2.setTime(df.parse(s2));
    	}catch(ParseException e){
    		//System.err.println("格式不正确");
    		return 2;
    	}
    	int result=c1.compareTo(c2);
        if(result==0)
        {
            return 0;
        } else {
            return result < 0 ? -1 : 1;
        }
    }

    /**
     * 计算两个日期间的天数
     */
    public static int getBetweenDays(String checkIn,String checkOut){
        long between_days = 0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date smdate= null;
        Date bdate= null;
        try {
            smdate = sdf.parse(checkIn);
            bdate = sdf.parse(checkOut);

            smdate=sdf.parse(sdf.format(smdate));
            bdate=sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24);
        } catch (ParseException e) {
            logger.error("计算两个日期间天数出错", e);
        }
        return (int)between_days;

    }

    /**
     * 取得星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }


    /**
     * 转换成UNIX TIMESTAMP 单位：秒
     */
    public static long date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static String getDateWeekByTimeStamp(long timeStamp){
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Date date = new Date(timeStamp);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int week = instance.get(Calendar.DAY_OF_WEEK) - 1;
        if(week < 0){
            week = 0;
        }
        return weekOfDays[week];
    }

    //获取本月的最后一天
    public  static String   getLastMonthDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format(calendar.getTime());
    }

    //获取本月第一天
    public  static  String getFisrtMonthDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return format(calendar.getTime());
    }

    //获取一个星期前的时间
    public  static  String getWeekDay(){
        Date weekBeginDate = addDay(new Date(), -6);
        return format(weekBeginDate);
    }

}
