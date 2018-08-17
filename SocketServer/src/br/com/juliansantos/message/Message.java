/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.message;

import br.com.juliansantos.JASDateHour.JASDateHour;

/**
 *
 * @author Julian Santos
 */
public class Message {
    
    public static final int TYPE_DEBUG = '0';
    public static final int TYPE_LOGIN = '1';
    
    private String date;
    private String hour;
    private int typrMessage;
    private String message;
    private boolean messageReady;
    
    public Message(int typeMessage, String message) {
        this.date = "";
        this.hour = "";
        this.messageReady = false;
        this.typrMessage = typeMessage;
        this.message = message;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getTyprMessage() {
        return typrMessage;
    }

    public void setTyprMessage(int typrMessage) {
        this.typrMessage = typrMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMessageReady() {
        return messageReady;
    }

    public void setMessageReady(boolean messageReady) {
        this.messageReady = messageReady;
    }

    public void read() {
        date = JASDateHour.currentDate();
        hour = JASDateHour.currentHour();
        messageReady = true;
    }
}
