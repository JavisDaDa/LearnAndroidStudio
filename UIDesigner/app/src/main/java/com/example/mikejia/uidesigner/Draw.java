package com.example.mikejia.uidesigner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.mikejia.uidesigner.MainActivity.holder;
import static com.example.mikejia.uidesigner.MainActivity.msurfaceView;
import static com.example.mikejia.uidesigner.MainActivity.paint;

public class Draw {
    private static final int X_OFFSET = 50;
    private static float[] mdata = DataTransfer.getData();
    public static int cx = X_OFFSET;
    private static Timer timer = new Timer();
    public static TimerTask task = null;
    public static double ratio = 1;

    public static void draw(){
        drawBack(holder);
        if(task != null){
            task.cancel();
        }

        task = new TimerTask() {

            @Override
            public void run() {
                if(mdata.length != 0){
                    int index = (int)((cx -X_OFFSET) * ratio);
                    int centerY = msurfaceView.getHeight() / 2;
                    Log.i("jydd", String.valueOf(centerY));
                    float cy = centerY - mdata[index] * centerY / 2;
                    if((cx - X_OFFSET) <= mdata.length - 1){
                        Canvas canvas = holder.lockCanvas(new Rect(cx, 0, cx+mdata.length, msurfaceView.getHeight()));
                        canvas.drawPoint(cx, cy, paint);
                        cx++;
                        Log.i("jyd", ":" + cx + "宽：" + msurfaceView.getWidth() + "高："+msurfaceView.getHeight() + "数据长度:" + mdata.length);
                        if(cx >= mdata.length / ratio){
                            cx = X_OFFSET;
                            task.cancel();
                        }
                        holder.unlockCanvasAndPost(canvas);
                    }else {
                        task.cancel();
                    }
                }else{
                    task.cancel();
                }
            }
        };
        timer.schedule(task, 0, 30);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                drawBack(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                timer.cancel();
            }
        });
    }
    //定义画布方法
    public static void drawBack(SurfaceHolder holder){
        Canvas canvas = holder.lockCanvas();
        //绘制黑色背景
        canvas.drawColor(Color.BLACK);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(2);
        //画网格8*8
        Paint mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);//网格为黄色
        mPaint.setStrokeWidth(1);//设置笔粗细
        int centerY = msurfaceView.getHeight() / 2;
        int oldY = 0;
        //绘制坐标轴
        //横轴
        canvas.drawLine(0, centerY, msurfaceView.getWidth(), centerY, p);
        //纵轴
        canvas.drawLine(X_OFFSET, 0, X_OFFSET, msurfaceView.getHeight(), p);
        holder.unlockCanvasAndPost(canvas);
        holder.lockCanvas(new Rect(0, 0, 0, 0));
        holder.unlockCanvasAndPost(canvas);
    }
}
