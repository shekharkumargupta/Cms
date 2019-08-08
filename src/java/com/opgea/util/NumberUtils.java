/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Ramesh
 */
public class NumberUtils {
    
    public static Integer getLacs(Integer amount){
        if(amount > 100000){
            return amount/100000;
        }else{
            return 0;
        }
    }
    
    public static Integer getThousands(Integer amount){
        if(amount > 100000){
            return (amount%100000)/1000;
        }
        if(amount < 100000 && amount > 999){
            return amount/1000;
        }else{
            return 0;
        }
    }
    
    public static Integer getLacs(Float amount){
        if(amount > 1){
            NumberFormat formater = new DecimalFormat("#");
            return Integer.parseInt(formater.format(amount/1));
        }else{
            return 0;
        }
    }
    
    public static Integer getThousands(Float amount){
        if(amount > 1){
            NumberFormat formater = new DecimalFormat("#");
            return Integer.parseInt(formater.format(amount%1));
        }
        else{
            return 0;
        }
    }
    
    public static Integer getYears(Float years){
        if(years > 1){
            NumberFormat formater = new DecimalFormat("#");
            return Integer.parseInt(formater.format(years/1));
        }else{
            return 0;
        }
    }
    
    public static Integer getMonths(Float years){
        if(years > 1){
            NumberFormat formater = new DecimalFormat("#");
            return Integer.parseInt(formater.format(years%1));
        }
        else{
            return 0;
        }
    }
}
