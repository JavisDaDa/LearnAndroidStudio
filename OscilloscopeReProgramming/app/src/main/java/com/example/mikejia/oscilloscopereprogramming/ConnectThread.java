package com.example.mikejia.oscilloscopereprogramming;

import android.widget.EditText;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static com.example.mikejia.oscilloscopereprogramming.MainActivity.socket;

public class ConnectThread extends Thread {
//    public static OutputStream mOutputStream = MainActivity.OutputStream;//定义数据输出流,用于发出去
//    public static InputStream mInputStream = MainActivity.InputStream;//定义数据输入流,用于写进来

//    private Socket socket = MainActivity.socket;
    public void run(){
        Connect.connect();
    }
}
