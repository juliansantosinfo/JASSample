/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Calendar;

/**
 *
 * @author Julian
 */
public class MessageConnection {
    
    private static final int IN = 1;
    private static final int OUT = 1;
    
    private Calendar c = Calendar.getInstance();
    private String date;
    private String hour;
    private int typrMessage;
    private String message;
    
}
