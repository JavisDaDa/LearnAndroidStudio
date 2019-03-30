package com.example.mikejia.oscilloscopereprogramming;

import java.net.InetAddress;
import java.net.Socket;

import static com.example.mikejia.oscilloscopereprogramming.MainActivity.socket;

public class Connect {
    private static InetAddress ipAddress;

    public static void connect(){
        try {
            //判断socket的状态
            if(socket== null){
                //如果socket为空则执行
                //获取输入的ip地址
                ipAddress = InetAddress.getByName(MainActivity.ip_EditText.getText().toString());
                //获取端口地址
                int port = Integer.valueOf(MainActivity.port_EditText.getText().toString());
                //新建一个socket
                socket = new Socket(ipAddress, port);
                //获取socket的输入流和输出流
                MainActivity.InputStream = socket.getInputStream();
                MainActivity.OutputStream = socket.getOutputStream();
                ThreadRead Read = new ThreadRead();
                Read.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
