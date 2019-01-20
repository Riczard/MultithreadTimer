package com.codecool.controller;

import com.codecool.model.TimerThread;
import com.codecool.view.PrintingContent;

import java.util.Map;
import java.util.Scanner;

public class TimerController {
    private Scanner sc = new Scanner(System.in);

    public void run() {
        boolean isRunning = true;
        while (isRunning){
            PrintingContent.printMenu();
            String input = getInput();
            isRunning = startProperFunction(input);
        }
    }


    private boolean startProperFunction(String functionName) {

        switch (functionName) {
            case "1":
                startTimer();
                break;
            case "2":
                stopTimer();
                break;
            case "3":
                checkTimer();
                break;
            case "4":
                checkAllTimers();
                break;
            case "0":
                return false;
            default:
                PrintingContent.printText("No Option like this");
        }
        return true;
    }

    private void checkAllTimers() {
        Map<String, TimerThread> timerMap = TimerThread.getTimerMap();
        PrintingContent.printText(timerMap);
    }

    private void stopTimer() {
        String timerName = getInput("Enter timer name");
        TimerThread timerThread = getTimerByName(timerName);
        timerThread.shutDown();
    }

    private void checkTimer() {
        String timerName = getInput("Enter timer name");
        TimerThread timerThread = getTimerByName(timerName);
        PrintingContent.printText(timerThread.getTimerInfo());
    }

    private void startTimer() {
        String timerName = getInput("Enter timer name");
        TimerThread timer = getTimerByName(timerName);
        if(timer != null){
            timer.setSecondsTo0();
        }else {
            TimerThread newTimer = new TimerThread();
            Thread t = new Thread(newTimer, timerName);
            t.start();
        }
    }

    private String getInput() {
        return sc.nextLine();
    }

    private String getInput(String text) {
        PrintingContent.printText(text);
        return sc.nextLine();
    }

    private TimerThread getTimerByName(String timerName) {
        Map<String, TimerThread> timerMap = TimerThread.getTimerMap();
        return timerMap.get(timerName);
    }
}
