package com.example.mikejia.uidesigner;

public class ThreadSendData extends Thread {
    public String content;
    public ThreadSendData(String content){
        this.content = content.concat("\r\n");
    }
    synchronized public void run(){
        try {
            MainActivity.OutputStream.write(content.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
