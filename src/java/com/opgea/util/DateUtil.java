/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ramesh
 */
public class DateUtil {
    
    public static String getHHmmss(Long timeMilliSeconds){
        Date date = new Date(timeMilliSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return sdf.format(date);
    }
    
    public static synchronized String getDateTimeString(Long timeMilliSeconds){
        Date date = new Date(timeMilliSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return sdf.format(date);
    }
    
    public static synchronized Date getDateFromYYYYMMDD(String dateString, String seperator){
        
        String valueArray[] = dateString.split(seperator); 
        int year = Integer.parseInt(valueArray[0]);
        int month = Integer.parseInt(valueArray[1]);
        int date = Integer.parseInt(valueArray[2]);
        
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, date);
        Date newDate = cal.getTime();
        System.out.println("Date: "+newDate);
        return newDate;
    }
    
    public static synchronized String getYYYYMMDDFromDate(Date date, String seperator){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"+seperator+"MM"+seperator+"dd");
        String dateString = dateFormat.format(date);
        return dateString;
    }
    
    public static void main(String args){
        
        System.out.println(DateUtil.getDateTimeString(System.currentTimeMillis()));
    }
}
