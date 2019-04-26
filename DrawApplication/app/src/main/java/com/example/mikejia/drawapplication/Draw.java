package com.example.mikejia.drawapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.mikejia.drawapplication.MainActivity.holder;
import static com.example.mikejia.drawapplication.MainActivity.msurfaceView;
import static com.example.mikejia.drawapplication.MainActivity.paint;

public class Draw {
    private static final int X_OFFSET = 50;
    //    public static Float[] mdata;
    //private static float[] mdata = {-7.87475f,-7.85522f, -7.81616f, -7.76538f, -7.69506f, -7.60913f, -7.51538f, -7.41381f, -7.29663f, -7.16381f, -7.01147f, -6.84741f, -6.67163f, -6.48803f, -6.28491f};
    private static float[] mdata = DataTransfer.getData();
//    private static int[] mdata = DataTransfer.getData();
//    private static double cy;
    public static int cx = X_OFFSET;
    private static Timer timer = new Timer();
    public static TimerTask task = null;

    //    final double[] data
    public static void draw(){

//        //如果data不为空，则把data里的数据转入mdata
//        if(MainActivity.data != null){
//            mdata = MainActivity.data.toArray(new Float[MainActivity.data.size()]);
//        }

        drawBack(holder);
//        mdata = data;
        if(task != null){
            task.cancel();
        }

        task = new TimerTask() {

            @Override
            public void run() {
                if(mdata.length != 0){
                    int index = cx -X_OFFSET;
                    int centerY = msurfaceView.getHeight() / 2;
//                    int cy = centerY - mdata[index] * 120;
                    float cy = centerY - mdata[index] * 120;
//                float voltage = ((cy - 3.2768f) * 6.1035f) - 1.8f;
//                float time = ((index - 0) * 2) - 1;
                    Canvas canvas = holder.lockCanvas(new Rect(cx, 0, cx+mdata.length, msurfaceView.getHeight()));
                    canvas.drawPoint(cx, cy, paint);
                    cx++;
                    if(cx >= msurfaceView.getWidth()){
                        cx = X_OFFSET;
                    }
                    holder.unlockCanvasAndPost(canvas);
                }else{
                    task.cancel();
                    task = null;
                }
//                cy = data[cx];
//                Canvas canvas = holder.lockCanvas(new Rect(cx, (int)cy - 2, cx + 2, (int) cy + 2));
//                canvas.drawPoint(cx, (float)cy, paint);
//                cx++;

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
//        //绘制横线
//        for(int i = 0; i <= 8; i++){
//            canvas.drawLine(0, oldY, msurfaceView.getWidth(), oldY, mPaint);
//            oldY += msurfaceView.getWidth()/8;
//        }
//        //绘制纵线
//        int oldX = 0;
//        for (int i = 0; i <= 8; i++){
//            canvas.drawLine(oldX, 0, oldX, msurfaceView.getHeight(), mPaint);
//            oldX += msurfaceView.getHeight()/8;
//        }
        //绘制坐标轴
        //横轴
        canvas.drawLine(X_OFFSET, centerY, msurfaceView.getWidth(), centerY, p);
        //纵轴
        canvas.drawLine(X_OFFSET, 0, X_OFFSET, msurfaceView.getHeight(), p);
        holder.unlockCanvasAndPost(canvas);
        holder.lockCanvas(new Rect(0, 0, 0, 0));
        holder.unlockCanvasAndPost(canvas);
    }
}
