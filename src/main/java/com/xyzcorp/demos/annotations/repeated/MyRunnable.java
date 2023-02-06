package com.xyzcorp.demos.annotations.repeated;

@Schedule(dayOfMonth="last")
@Schedule(dayOfWeek="Fri", hour=23)
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Do something here");
    }
}
