package com.myshop2.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TimerThread extends Thread {

    protected boolean isRunning = true;
    protected JLabel[] timeLabel;

    protected SimpleDateFormat dateFormat = 
            new SimpleDateFormat("EEE, d MMM yyyy");
    protected SimpleDateFormat timeFormat =
            new SimpleDateFormat("HH:mm a");

    public TimerThread(JLabel... timeLabel) {
        this.timeLabel = timeLabel;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Calendar currentCalendar = Calendar.getInstance();
                    Date currentTime = currentCalendar.getTime();
                    for(JLabel label : timeLabel)
                    	label.setText(timeFormat.format(currentTime));
                }
            });

            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
