package com.example.mikejia.oscilloscopereprogramming;

public class ThreadDraw extends Thread {
    @Override
    public void run() {
        super.run();
        Draw.draw();
    }
}
