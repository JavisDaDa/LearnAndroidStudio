package com.example.mikejia.oscilloscopereprogramming;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //变量
    public static EditText ip_EditText;//ip编辑对象
    public static EditText port_EditText;//端口对象
    public static EditText receive_EditText;
    public static EditText datatoedit;
    public static Socket socket = null;// Socket变量
    public static OutputStream OutputStream = null;//定义数据输出流,用于发出去
    public static InputStream InputStream = null;//定义数据输入流,用于写进来
    public static ArrayList<Float> data = new ArrayList<>();

    public static SurfaceView msurfaceView;
    public static SurfaceHolder holder;
    public static Paint paint;
    Button button_connect;//链接服务器按钮对象
    Button button_send;
    Button button_draw;
    boolean buttontitle = true;//定义一个逻辑变量,用于判断连接服务器按钮状态
    boolean buttontitle2 = true;
    public static boolean RD = false;//用于控制取数据线程是否执行
    ThreadReadData ReadData = new ThreadReadData();
    ThreadSendData IDN = new ThreadSendData("*IDN?");
    ThreadSendData CLS = new ThreadSendData("*CLS");
    ThreadSendData RST = new ThreadSendData("*RST");
    ThreadSendData ESE = new ThreadSendData("*ESE");
    ThreadSendData ESE2 = new ThreadSendData("*ESE?");
    ThreadSendData ESR2 = new ThreadSendData("*ESR?");
    ThreadSendData OPC = new ThreadSendData("*OPC");
    ThreadSendData OPC2 = new ThreadSendData("*OPC?");
    ThreadSendData SRE = new ThreadSendData("*SRE");
    ThreadSendData SRE2 = new ThreadSendData("*SRE?");
    ThreadSendData STB2 = new ThreadSendData("*STB?");
    ThreadSendData TST2 = new ThreadSendData("*TST?");
    ThreadSendData WAI = new ThreadSendData("*WAI");
    ThreadSendData AUToscale = new ThreadSendData(":AUToscale");
    ThreadSendData ChanelProbe = new ThreadSendData(":CHANnel1:PROBe 10");
    ThreadSendData ChanelRange = new ThreadSendData(":CHANnel1:RANGe 8");
    ThreadSendData MeasureSource = new ThreadSendData(":MEASure:SOURce CHANnel1");
    ThreadSendData MeasureFrequency2 = new ThreadSendData(":MEASure:FREQuency?");
    ThreadSendData MeasureVpp2 = new ThreadSendData(":MEASure:VPP?");
    ThreadSendData WaveFormFormatByte = new ThreadSendData(":WAVeform:FORMat BYTE");
    ThreadSendData WaveFormPoints = new ThreadSendData(":WAVeform:POINts 1000");
    ThreadSendData WaveFormPreamble2 = new ThreadSendData(":WAVeform:PREamble?");
    ThreadSendData WaveFormData2 = new ThreadSendData(":WAVeform:DATA?");
    ThreadSendData AcquireType = new ThreadSendData(":ACQuire:TYPE NORMal");
    ThreadSendData AcquireComplete = new ThreadSendData(":ACQuire:COMPlete 100");
    ThreadSendData Digitize = new ThreadSendData(":DIGitize CHANnel1");
    ThreadSendData WaveFormFormatAscii = new ThreadSendData(":WAVeform:FORMat ASCii");
    //并创建一个新的线程，用于初始化socket


//    ThreadSendData OPC2 = new ThreadSendData("*IDN?");
//    ThreadSendData SRE = new ThreadSendData("*IDN?");
//    ThreadSendData SRE2 = new ThreadSendData("*IDN?");
//    ThreadSendData STB2 = new ThreadSendData("*IDN?");
//    ThreadSendData TST2 = new ThreadSendData("*CLS");
//    ThreadSendData WAI = new ThreadSendData("*IDN?");
//    ThreadSendData AUToscale = new ThreadSendData("*IDN?");
//    ThreadSendData ChanelProbe = new ThreadSendData("*IDN?");
//    ThreadSendData ChanelRange = new ThreadSendData("*IDN?");
//    ThreadSendData MeasureSource = new ThreadSendData("*IDN?");
//    ThreadSendData MeasureFrequency = new ThreadSendData("*IDN?");
//    ThreadSendData MeasureVpp = new ThreadSendData("*IDN?");
//    ThreadSendData WaveFormFormat = new ThreadSendData("*IDN?");
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
        msurfaceView = findViewById(R.id.SurfaceView_show);
        holder = msurfaceView.getHolder();
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(5);

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
                            IDN.start();
                            break;
                        case 1:
                            CLS.start();
                            break;
                        case 2:
                            RST.start();
                            break;
                        case 3:
                            ESE.start();
                            break;
                        case 4:
                            ESE2.start();
                            break;
                        case 5:
                            ESR2.start();
                            break;
                        case 6:
                            OPC.start();
                            break;
                        case 7:
                            OPC2.start();
                            break;
                        case 8:
                            SRE.start();
                            break;
                        case 9:
                            SRE2.start();
                            break;
                        case 10:
                            STB2.start();
                            break;
                        case 11:
                            TST2.start();
                            break;
                        case 12:
                            WAI.start();
                            break;
                        case 13:
                            AUToscale.start();
                            break;
                        case 14:
                            ChanelProbe.start();
                            break;
                        case 15:
                            ChanelRange.start();
                            break;
                        case 21:
                            MeasureSource.start();
                            break;
                        case 22:
                            MeasureFrequency2.start();
                            break;
                        case 23:
                            MeasureVpp2.start();
                            break;
                        case 24:
                            WaveFormFormatByte.start();
                            break;
                        case 25:
                            WaveFormPoints.start();
                            break;
                        case 26:
                            WaveFormPreamble2.start();
                            break;
                        case 27:
                            WaveFormData2.start();

                            break;
                        case 28:
                            AcquireType.start();
                            break;
                        case 29:
                            AcquireComplete.start();
                            break;
                        case 30:
                            Digitize.start();
                            break;
                        case 31:
                            WaveFormFormatAscii.start();
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
    }


    //链接按钮按下
    public void connect(View view) {
        //判断按钮状态
        if(buttontitle == true){
            //如果被按下，修改状态为按下
            buttontitle = false;
            //读数据线程可以执行
            RD = true;
            ConnectThread Connect_thread = new ConnectThread();
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
    //绘图按钮按下
    public void analyze(View view) throws IOException{
        if(buttontitle2 == true){
            buttontitle2 = false;
            ThreadDraw draw = new ThreadDraw();
            draw.start();
            button_draw.setText("结束绘图");
        }else {
            button_draw.setText("开始绘图");
            buttontitle2 = true;
        }
//        ThreadDraw draw = new ThreadDraw();
//        draw.start();

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
}

