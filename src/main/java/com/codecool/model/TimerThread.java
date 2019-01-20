package com.codecool.model;

import java.util.HashMap;
import java.util.Map;

public class TimerThread implements Runnable {

    private static Map<String, TimerThread> timerMap = new HashMap<>();
    private int seconds = 0;
    private long threadId;
    private String timerName;
    private volatile boolean running = true;

    public static Map<String, TimerThread> getTimerMap() {
        return timerMap;
    }

    public void setSecondsTo0() {
        this.seconds = 0;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        threadId = Thread.currentThread().getId();
        timerName = Thread.currentThread().getName();
        timerMap.put(thread.getName(), this);

        while (running) {
            seconds++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void shutDown() {
        running = false;
    }

    public String getTimerInfo() {
        return "Timer name: " + timerName +
                ", ThreadID: " + threadId +
                ", Seconds: " + seconds;
    }
}
