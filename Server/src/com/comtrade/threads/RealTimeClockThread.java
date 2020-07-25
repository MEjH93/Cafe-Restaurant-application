package com.comtrade.threads;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;


public class RealTimeClockThread extends Thread {
		
	private final JLabel label1;

    public RealTimeClockThread(JLabel label1) {
    	
        this.label1 = label1;
        
       
        start();
    }

    @Override
    public void run() {
        while(true){

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            label1.setText(simpleDateFormat.format(new Date()));

        }
   
    		}
}