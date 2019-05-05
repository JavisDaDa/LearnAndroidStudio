package com.example.mikejia.uidesigner;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Connect所需变量
    //变量
    public static EditText ip_EditText;//ip编辑对象
    public static Socket socket = null;// Socket变量
    public static EditText receive_EditText;
    public static java.io.OutputStream OutputStream = null;//定义数据输出流,用于发出去
    public static java.io.InputStream InputStream = null;//定义数据输入流,用于写进来
    public static InputStreamReader inputStreamReader = null;
    public static BufferedReader bufferedReader = null;
    public static boolean RD = false;//用于控制取数据线程是否执行
    public static StringBuilder data = new StringBuilder();
    boolean buttontitle2 = true, buttontitle = true;
    Button btn_draw, btn_autoScale, btn_measure, btn_channel, btn_trigger, btn_connect, btn_ascii, btn_data, btn_data2, btn_minimize, btn_maximize, btn_clear;   //按钮定义
    //定义画图相关变量
    public static SurfaceView msurfaceView;
    public static SurfaceHolder holder;
    public static Paint paint;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        setContentView(R.layout.fragment_main);
        //按钮初始化
        btn_draw= findViewById(R.id.btn_draw);
        btn_autoScale = findViewById(R.id.btn_autoScale);
        btn_measure = findViewById(R.id.btn_measure);
        btn_channel = findViewById(R.id.btn_channel);
        btn_trigger = findViewById(R.id.btn_trigger);
        btn_ascii = findViewById(R.id.btn_ascii);
        btn_data = findViewById(R.id.btn_data);
        btn_data2 = findViewById(R.id.btn_data2);
        btn_minimize = findViewById(R.id.btn_minimize);
        btn_maximize = findViewById(R.id.btn_maximize);
        btn_clear = findViewById(R.id.btn_clear);
        //定义按钮监听
        btn_draw.setOnClickListener(this);
        btn_autoScale.setOnClickListener(this);
        btn_measure.setOnClickListener(this);
        btn_channel.setOnClickListener(this);
        btn_trigger.setOnClickListener(this);
        btn_ascii.setOnClickListener(this);
        btn_data.setOnClickListener(this);
        btn_data2.setOnClickListener(this);
        btn_minimize.setOnClickListener(this);
        btn_maximize.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        //编辑框初始化
        receive_EditText = findViewById(R.id.editText_message);
        //初始化右侧碎片
        replaceFragment(new RightFragment());
        btn_connect = findViewById(R.id.button_connect);
        //获取ip输入框
        ip_EditText = findViewById(R.id.editText_ip);
        //初始化画图相关变量
        msurfaceView = findViewById(R.id.SurfaceView_show);
        holder = msurfaceView.getHolder();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        //Spinner功能
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
                            Log.i("jyd", "1");
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
            btn_connect.setText("断开连接");
        }else{
            //如果按钮已经按下，则改变按钮标题
            btn_connect.setText("连接服务器");
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_draw:
                if(buttontitle2 == true){
//            String str = DataTransfer.mShow;
//            Log.i("String", str);
                    buttontitle2 = false;
                    ThreadDraw draw = new ThreadDraw();
                    draw.start();
                    btn_draw.setText("结束绘图");
                }else {
                    Draw.cx = 50;
                    Draw.task.cancel();
                    Draw.task = null;
                    Draw.drawBack(holder);
                    btn_draw.setText("开始绘图");
                    buttontitle2 = true;
                }
                break;
            case R.id.btn_autoScale:
                AUToscale.start();
                break;
            case R.id.btn_measure:
                replaceFragment(new MeasureRightFragment());
                break;
            case R.id.btn_channel:
                replaceFragment(new ChannelRightFragment());
                break;
            case R.id.btn_trigger:
                replaceFragment(new TriggerRightFragment());
                break;
            case R.id.btn_ascii:
                WaveFormFormatAscii.start();
                break;
            case R.id.btn_data:
                WaveFormPoints.start();
                break;
            case R.id.btn_data2:
                WaveFormData2.start();
                break;
            case R.id.btn_minimize:
                Draw.ratio *= 2;
                break;
            case R.id.btn_maximize:
                Draw.ratio /= 2;
                break;
            case R.id.btn_clear:
                if (data != null){
                    data = null;
                }
                break;
            default:
                break;
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        //返回栈
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //验证编辑框内容是否合法
    public boolean thisif() {
        //定义一个信息框留作备用
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setPositiveButton("确定", click1);
        //分别获取ip、端口、数据这三项的内容
        String ip = ip_EditText.getText().toString();
        //判断是否有编辑框为空
        if (ip == null || ip.length() == 0){
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
