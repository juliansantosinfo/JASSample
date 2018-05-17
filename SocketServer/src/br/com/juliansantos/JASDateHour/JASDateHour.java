package br.com.juliansantos.JASDateHour;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Julian
 */
public class JASDateHour {

    public static final String dataFormatDefault = "dd/mm/yyyy";
    public static final String hourFormatDefault = "HH:mm:ss";

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat simpleDateFormat;
    private static Date date = new Date();

    public JASDateHour() {
    }

    public static Calendar getCalendar() {
        return calendar;
    }

    public static void setCalendar(Calendar calendar) {
        JASDateHour.calendar = calendar;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public static void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        JASDateHour.simpleDateFormat = simpleDateFormat;
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        JASDateHour.date = date;
    }

    public static String currentDate() {
        simpleDateFormat = new SimpleDateFormat(dataFormatDefault);
        return simpleDateFormat.format(date);
    }

    public String getDateInFormat(String format) {
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String currentHour() {
        simpleDateFormat = new SimpleDateFormat(hourFormatDefault);
        return simpleDateFormat.format(date);
    }

    public String getHourInFormat(String format) {
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
