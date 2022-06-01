package com.example.foodfinder.threads;

import android.os.CountDownTimer;

import com.example.foodfinder.interfaces.TimerListener;

public class TimerThread implements Runnable{

    private int miliseconds;
    private int interval;
    private CountDownTimer countDownTimer;
    private TimerListener listener;

    public TimerThread(int miliseconds, int interval, TimerListener listener) {
        this.miliseconds = miliseconds;
        this.interval = interval;
        this.listener = listener;

        countDownTimer = new CountDownTimer(this.miliseconds, this.interval) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                listener.onFinish();
                start();
            }
        };
    }
    @Override
    public void run() {
        countDownTimer.start();
    }
}
