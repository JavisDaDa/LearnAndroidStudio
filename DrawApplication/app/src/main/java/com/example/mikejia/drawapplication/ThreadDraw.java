package com.example.mikejia.drawapplication;

public class ThreadDraw extends Thread{
    @Override
    public void run() {
        super.run();
        Draw.draw();
    }
}
