package com.example.mikejia.uidesigner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Connect所需变量
    //变量
    public static EditText ip_EditText;//ip编辑对象
    public static Socket socket = null;// Socket变量
    public static java.io.OutputStream OutputStream = null;//定义数据输出流,用于发出去
    public static java.io.InputStream InputStream = null;//定义数据输入流,用于写进来
    public static boolean RD = false;//用于控制取数据线程是否执行
    Switch mSwitch;                 //选择开关
    Button btn_draw, btn_autoScale, btn_measure, btn_channel, btn_trigger, btn_measure_back;   //按钮定义
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        //按钮初始化
        btn_draw= findViewById(R.id.btn_draw);
        btn_autoScale = findViewById(R.id.btn_autoScale);
        btn_measure = findViewById(R.id.btn_measure);
        btn_channel = findViewById(R.id.btn_channel);
        btn_trigger = findViewById(R.id.btn_trigger);
        //定义按钮监听
        btn_draw.setOnClickListener(this);
        btn_autoScale.setOnClickListener(this);
        btn_measure.setOnClickListener(this);
        btn_channel.setOnClickListener(this);
        btn_trigger.setOnClickListener(this);
        //初始化右侧碎片
        replaceFragment(new RightFragment());
        mSwitch = findViewById(R.id.switch_connect);
        //获取ip输入框
        ip_EditText = findViewById(R.id.editText_ip);
        //选择开关的监听器
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //读数据线程可以执行
                    RD = true;
                    ConnectThread Connect_thread = new ConnectThread();
                    Connect_thread.start();
                }else {
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
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_draw:
                break;
            case R.id.btn_autoScale:
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
}
