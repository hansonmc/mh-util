package com.mh.util.date;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by menghan on 2017/5/21.
 */
public class DateUtil {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static String YYYYMMDDHHMMSS= "yyyyMMddHHmmss";

    /**
     * 初始化 年 月 日
     * 时分秒 设置为0
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date setDate(int year, int month, int day){
        return setDate(year , month,day,0,0,0);
    }

    /**
     * 初始化时间 年 月 日 时 分 秒
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date setDate(int year, int month, int day, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    /**
     * 将日期用默认格式解析为字符串
     * @param date
     * @return
     */
    public static String format(Date date){
        return format(date,DEFAULT_FORMAT);
    }

    /**
     * 将日期按指定格式解析为字符串
     * @param date
     * @param formatStr
     * @return
     */
    public static String format(Date date,String formatStr){
        SimpleDateFormat sdf = null;
        if(StringUtils.isEmpty(formatStr)){
            sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        }else{
            sdf = new SimpleDateFormat(formatStr);
        }
        return sdf.format(date);
    }

    /**
     * 当前时间，格式：yyyyMMddHHmmss
     * @return
     */
    public static String getCurrentDatetime() {
        return new SimpleDateFormat(YYYYMMDDHHMMSS).format( new Date());
    }

    /**
     * 得到当前月的天数
     * @return
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到指定月的天数
     * */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

	/**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 循环日期模板
     */
    public static void loop(){
        Date beginDate = setDate(2017,5,1);
        Date endDate = setDate(2017,5,31);
        int i = 1;
        for(Date indexDate = beginDate; indexDate.getTime() <= endDate.getTime(); indexDate = setDate(2017,5,++i)){
            System.out.println(format(indexDate));
        }
    }

    public static void main(String[] args) {
        System.out.println(getCurrentMonthLastDay());
        System.out.println(getMonthLastDay(2017,0));
    }
}
