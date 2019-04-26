package com.example.mikejia.drawapplication;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean buttontitle2 = true;
    public static SurfaceView msurfaceView;
    public static SurfaceHolder holder;
    public static Paint paint;
    Button button_draw;
    //定义一个中转Float的ArrayList
    public static ArrayList<float[]> inbuf = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msurfaceView = findViewById(R.id.SurfaceView_show);
        button_draw = findViewById(R.id.button_draw);
        holder = msurfaceView.getHolder();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
    }


    //绘图按钮按下
    public void analyze(View view) throws IOException {
        if(buttontitle2 == true){
//            String str = DataTransfer.mShow;
//            Log.i("String", str);
            buttontitle2 = false;
            ThreadDraw draw = new ThreadDraw();
            draw.start();
            button_draw.setText("结束绘图");
        }else {
            Draw.cx = 50;
            Draw.task.cancel();
            Draw.task = null;
            Draw.drawBack(holder);
            button_draw.setText("开始绘图");
            buttontitle2 = true;
        }
//        ThreadDraw draw = new ThreadDraw();
//        draw.start();

    }
}
