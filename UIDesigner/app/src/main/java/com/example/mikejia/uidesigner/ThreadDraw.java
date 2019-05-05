package com.example.mikejia.uidesigner;

public class ThreadDraw extends Thread{
    @Override
    public void run() {
        super.run();
        Draw.draw();
    }
}
