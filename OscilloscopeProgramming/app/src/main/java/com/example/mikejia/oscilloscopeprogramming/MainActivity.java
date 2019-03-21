package com.example.mikejia.oscilloscopeprogramming;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    //变量
    EditText ip_EditText;//ip编辑对象
    EditText port_EditText;//端口对象
    EditText receive_EditText;
    EditText datatoedit;
    Button button_connect;//链接服务器按钮对象
    Button button_send;
    Button button_draw;
    Socket socket = null;// Socket变量
    boolean buttontitle = true;//定义一个逻辑变量,用于判断连接服务器按钮状态
    boolean RD = false;//用于控制取数据线程是否执行
    OutputStream OutputStream = null;//定义数据输出流,用于发出去
    InputStream InputStream = null;//定义数据输入流,用于写进来

    //Surface View definition
    private SurfaceHolder holder;
    private Paint paint;
    private SurfaceView surfaceView;
    final int HEIGHT = 600;
    final int WIDTH = 800;
    final int X_OFFSET = 50;
    private int cx = X_OFFSET;

    //实际的Y轴的位置
    int centerY = HEIGHT / 2;
    Timer timer = new Timer();
    TimerTask task = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化ip,端口变量
        ip_EditText = findViewById(R.id.editText_ip);
        port_EditText = findViewById(R.id.editText_port);
        receive_EditText = findViewById(R.id.editText_message);
        datatoedit = findViewById(R.id.editText_send);

        //初始化按钮
        button_connect = findViewById(R.id.button_connect);
        button_send = findViewById(R.id.button_send);
        button_draw = findViewById(R.id.button_draw);


        Resources res =getResources();
        final String[] city=res.getStringArray(R.array.SCPI);//将province中内容添加到数组city中
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);//获取到spacer1
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);//创建Arrayadapter适配器
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//通过此方法为下拉列表设置点击事件
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(thisif()){
                    String text= spinner.getItemAtPosition(i).toString();
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                    switch (i){
                        case 0:
                            ThreadSendIDN t0 = new ThreadSendIDN();
                            t0.start();
                            break;
                        case 1:
                            ThreadSendCLS t1 = new ThreadSendCLS();
                            t1.start();
                            break;
                        case 2:
                            ThreadSendRST t2 = new ThreadSendRST();
                            t2.start();
                            break;
                        case 3:
                            ThreadSendESE t3 = new ThreadSendESE();
                            t3.start();
                            break;
                        case 4:
                            ThreadSendESE2 t4 = new ThreadSendESE2();
                            t4.start();
                            break;
                        case 5:
                            ThreadSendESR2 t5 = new ThreadSendESR2();
                            t5.start();
                            break;
                        case 6:
                            ThreadSendOPC t6 = new ThreadSendOPC();
                            t6.start();
                            break;
                        case 7:
                            ThreadSendOPC2 t7 = new ThreadSendOPC2();
                            t7.start();
                            break;
                        case 8:
                            ThreadSendSRE t8 = new ThreadSendSRE();
                            t8.start();
                            break;
                        case 9:
                            ThreadSendSRE2 t9 = new ThreadSendSRE2();
                            t9.start();
                            break;
                        case 10:
                            ThreadSendSTB2 t10 = new ThreadSendSTB2();
                            t10.start();
                            break;
                        case 11:
                            ThreadSendTST2 t11 = new ThreadSendTST2();
                            t11.start();
                            break;
                        case 12:
                            ThreadSendWAI t12 = new ThreadSendWAI();
                            t12.start();
                            break;
                    }
                }else{
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }//end onCreate


    //发送数据按钮按下
    public void play(View view){
        //验证用户编辑是否合法
        if(thisif()){
            //创建线程用于发送数据
            ThreadSendData t1 = new ThreadSendData();
            t1.start();
        }else{
            return;
        }
    }

    //初始化按钮按下
    public void initialize(View view){
        ThreadInitialize t0 = new ThreadInitialize();
        t0.start();
    }

    //获取数据按钮按下
    public void capture(View view){
        ThreadCapture t0 = new ThreadCapture();
        t0.start();
    }
    //绘图按钮按下
    public void analyze(View view){
//        ThreadAnalyze t0 = new ThreadAnalyze();
//        t0.start();
        ThreadDraw t2 = new ThreadDraw();
        t2.start();
    }
    //链接按钮按下
    public void connect(View view) {
        //判断按钮状态
        if(buttontitle == true){
            //如果被按下，修改状态为按下
            buttontitle = false;
            //读数据线程可以执行
            RD = true;
            //并创建一个新的线程，用于初始化socket
            Connect_Thread Connect_thread = new Connect_Thread();
            Connect_thread.start();
            //改变按钮标题
            button_connect.setText("断开连接");
        }else{
            //如果按钮已经按下，则改变按钮标题
            button_connect.setText("连接服务器");
            //改变按钮的状态变量
            buttontitle = true;
            try {
                //取消socket
                socket.close();
                //socket设置为空
                socket = null;
                //读数据线程不执行
                RD = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //创建初始化线程
    class ThreadInitialize extends Thread{
        public void run(){
            ThreadSendRST t0 = new ThreadSendRST();
            t0.start();
            ThreadSendCLS t1 = new ThreadSendCLS();
            t1.start();
            ThreadSendIDN t2 = new ThreadSendIDN();
            t2.start();
            ThreadSendAUToscale t3 = new ThreadSendAUToscale();
            t3.start();
            ThreadSendChanelProbe t4 = new ThreadSendChanelProbe();
            t4.start();
            ThreadSendChanelRange t10 = new ThreadSendChanelRange();
            t10.start();
            ThreadSendTimebaseRange t5 = new ThreadSendTimebaseRange();
            t5.start();
            ThreadSendTimebaseReference t6 = new ThreadSendTimebaseReference();
            t6.start();
            ThreadSendTriggerTVSource t7 = new ThreadSendTriggerTVSource();
            t7.start();
            ThreadSendTriggerMode t8 = new ThreadSendTriggerMode();
            t8.start();
            ThreadSendTriggerEdgeSlope t9 = new ThreadSendTriggerEdgeSlope();
            t9.start();
        }
    }

    //创建获取数据线程
    class ThreadCapture extends Thread{
        public void run(){
            ThreadSendAcquireType t0 = new ThreadSendAcquireType();
            t0.start();
            ThreadSendAcquireComplete t1 = new ThreadSendAcquireComplete();
            t1.start();
            ThreadSendDigitize t2 = new ThreadSendDigitize();
            t2.start();
        }
    }

    //创建分析数据线程
    class ThreadAnalyze extends Thread{
        public void run(){

        }
    }
    //用线程创建SurfaceView画图
    class ThreadDraw extends Thread{
        public void run(){
            //初始化SurfaceView
            surfaceView = findViewById(R.id.SurfaceView_show);

            //初始化SurfaceHolder对象
            holder = surfaceView.getHolder();
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(5);

            drawBack(holder);
            cx = X_OFFSET;
            if (task != null) {
                task.cancel();
            }
            task = new TimerTask() {
                @Override
                public void run() {
                    int cy = centerY - (int) (200 * Math.cos((cx - X_OFFSET) * 2 * Math.PI / 400));
                    Canvas canvas = holder.lockCanvas(new Rect(cx, cy - 200, cx + 2, cy + 2));
                    canvas.drawPoint(cx, cy, paint);
                    cx++;

                    if (cx > surfaceView.getWidth()) {

                        task.cancel();
                        task = null;

                    }
                    holder.unlockCanvasAndPost(canvas);
                }
            };
            timer.schedule(task, 0, 30);

            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    drawBack(holder);
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    timer.cancel();
                }
            });
        }
    }

    //用线程创建Socket链接
    class Connect_Thread extends Thread {
        public void run() {

            //定义一个变量用于存储ip
            InetAddress ipAddress;
            try {
                //判断socket的状态,防止重复执行
                if (socket == null) {
                    //如果socket为空则执行
                    //获取输入的ip地址
                    ipAddress = InetAddress.getByName(ip_EditText.getText().toString());
                    //获取端口地址
                    int port = Integer.valueOf(port_EditText.getText().toString());
                    //新建一个socket
                    socket = new Socket(ipAddress, port);
                    //获取socket的输入流和输出流
                    InputStream = socket.getInputStream();
                    OutputStream = socket.getOutputStream();

                    //新建一个线程读数据
                    ThreadReadData t1 = new ThreadReadData();
                    t1.start();
//                    ThreadReadPreamble t2 = new ThreadReadPreamble();
//                    t2.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程执行读取服务器发来的数据
    class ThreadReadData extends Thread {
        public void run() {
            //定义一个变量用于储存服务器发来的数据
            String textdata;
            //根据RD的值来判断是否执行读数据
            while (RD) {
                try {
                    //定义一个字节集,存放输入的数据,缓存区大小为2048字节
                    final byte[] ReadBuffer = new byte[2048];
                    //用于存放数据量
                    final int ReadBufferLength;
                    //从输入流获取服务器发来的数据和数据宽度
                    //ReadBuffer为参考变量，在这里会改变为数据
                    //输入流的返回值是服务器发来的数据宽度
                    ReadBufferLength = InputStream.read(ReadBuffer);

                    //验证数据宽度,如果为-1则已经断开了链接
                    if (ReadBufferLength == -1) {
                        RD = false;
                        socket.close();
                        socket = null;
                    } else {
                        //如果有数据正常返回则进行处理显示
                        /*
                            这个地方有个很大的坑，让我搞了不少的时间
                            我用其他语言写的Web服务器程序，默认编码是gb2312
                            AS的默认编码是utf-8
                            在获取服务器发来的数据的时候，程序已经对这段gb2312的数据进行编码...
                            至于编码是什么就不知道了
                            我研究了很长时间，怎么转码也不对，越转越乱
                            最后测试出来是gb2312编码已经被转码了，我就先恢复gb2312编码
                            然后转成程序不会乱码的utf-8
                            如果目标服务器编码是utf8的话就不用转了
                        */

                        //先恢复成GB2312编码
                        textdata = new String(ReadBuffer, 0, ReadBufferLength, "GB2312");//原始编码数据
                        String textdata5 = new String(ReadBuffer, 6, 4,"GB2312");
                        String show0 = new String(textdata.getBytes(), "UTF-8");
                        StringBuilder show = new StringBuilder(show0);
                        StringBuilder show1 = new StringBuilder(String.valueOf(ReadBufferLength));
                        show = show.append("共").append(show1).append("个字符。").append("共").append(textdata5).append("个数据。");
                        //转为UTF-8编码后显示在编辑框中
                        receive_EditText.setText(show);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //用线程发送数据
    class ThreadSendData extends Thread{
        public void run(){
            try {
                //用输出流发送数据
                OutputStream.write((datatoedit.getText().toString() + "\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //用线程发送*IDN?
    class ThreadSendIDN extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*IDN?\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*CLS
    class ThreadSendCLS extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*CLS\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*RST
    class ThreadSendRST extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*RST\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*ESE
    class ThreadSendESE extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*ESE\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*ESE?
    class ThreadSendESE2 extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*ESE?\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*ESR?
    class ThreadSendESR2 extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*ESR?\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*OPC
    class ThreadSendOPC extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*OPC\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*OPC?
    class ThreadSendOPC2 extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*OPC?\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*SRE
    class ThreadSendSRE extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*SRE\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*SRE?
    class ThreadSendSRE2 extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*SRE?\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*STB?
    class ThreadSendSTB2 extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*STB?\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*TST?
    class ThreadSendTST2 extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*TST?\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送*WAI
    class ThreadSendWAI extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write(("*WAI\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:AUToscale
    class ThreadSendAUToscale extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":AUToscale\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:CHANnel1:PROBe 10
    class ThreadSendChanelProbe extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":CHANnel1:PROBe 10\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:CHANnel1:RANGe 8
    class ThreadSendChanelRange extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":CHANnel1:RANGe 8\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:TIMebase:RANGe 2e-3
    class ThreadSendTimebaseRange extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":TIMebase:RANGe 2e-3\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:TIMebase:REFerence CENTer
    class ThreadSendTimebaseReference extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":TIMebase:REFerence CENTer\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:TRIGger:TV:SOURCe CHANnel1
    class ThreadSendTriggerTVSource extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":TRIGger:TV:SOURCe CHANnel1\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:TRIGger:MODE EDGE
    class ThreadSendTriggerMode extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":TRIGger:MODE EDGE\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:TRIGger:EDGE:SLOPe POSitive
    class ThreadSendTriggerEdgeSlope extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":TRIGger:EDGE:SLOPe POSitive\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:ACQuire:TYPE NORMal
    class ThreadSendAcquireType extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":ACQuire:TYPE NORMal\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:ACQuire:COMPlete 100
    class ThreadSendAcquireComplete extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":ACQuire:COMPlete 100\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //用线程发送:DIGitize CHANnel1
    class ThreadSendDigitize extends Thread {
        public void run() {
            try {
                //用输出流发送数据
                OutputStream.write((":DIGitize CHANnel1\r\n").getBytes());
                //发送数据之后会自动断开连接，所以，恢复为最初的状态
                //有个坑要说一下，因为发送完数据还得等待服务器返回，所以，不能把Socket也注销掉
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //验证编辑框内容是否合法
    public boolean thisif() {
        //定义一个信息框留作备用
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setPositiveButton("确定", click1);
        //分别获取ip、端口、数据这三项的内容
        String ip = ip_EditText.getText().toString();
        String port = port_EditText.getText().toString();
        //判断是否有编辑框为空
        if (ip == null || ip.length() == 0 || port == null || port.length() == 0){
            //如果有空则弹出提示
            message.setMessage("各数据不能为空！");
            AlertDialog m1 = message.create();
            m1.show();
            return false;
        }else{
            return true;
        }
    }
    //信息框按钮按下事件
    public DialogInterface.OnClickListener click1 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    private void drawBack(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        //绘制白色背景
        canvas.drawColor(Color.BLACK);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(2);

        //绘制坐标轴
        canvas.drawLine(X_OFFSET, centerY, surfaceView.getWidth(), centerY, p);
        canvas.drawLine(X_OFFSET, 0, X_OFFSET, surfaceView.getHeight(), p);
        holder.unlockCanvasAndPost(canvas);
        holder.lockCanvas(new Rect(0, 0, 0, 0));
        holder.unlockCanvasAndPost(canvas);

    }
}
