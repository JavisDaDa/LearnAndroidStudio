package com.example.mikejia.oscilloscopereprogramming;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
public class DataProcessing {

//    public static String mshow;

//    private ArrayList<float[]> inBuf = new ArrayList<float[]>();
//    private boolean isTransfering = false;
//    /**
//     * X轴缩小的比例
//     */
//    public int rateX = 4;
//    /**
//     * Y轴缩小的比例
//     */
//    public int rateY = 4;
//    /**
//     * Y轴基线
//     */
//    public int baseLine = 0;
//    /**
//     * 初始化
//     */
//
//    public void initDataProcessing(int rateX, int rateY, int baseLine) {
//        this.rateX = rateX;
//        this.rateY = rateY;
//        this.baseLine = baseLine;
//    }
//
//    public void StartTransferData(){
//        isTransfering = true;
////        new TransferThread().start();
////        new DrawThread().start();
//    }
//
//    public void StopTransferData(){
//        isTransfering = false;
//        inBuf.clear();
//    }
//
//    class TransferThread extends Thread{
//        private int BufSize;
//        public TransferThread(int Bufsize){
//            this.BufSize = Bufsize;
//        }
//
//        public void run(){
//            float[] buffer = new float[BufSize];
//        }
//    }
    private static String data;
    DataProcessing(String data){
        this.data = data;
    }
    public static float[] DataSplit(){
        String str = data.substring(11);
        String[] arr = str.split(",");
        float[] result = new float[arr.length];
        for (int i = 0; i<result.length;i++) {
            result[i] = Float.valueOf(arr[i]);
        }
        return result;
    }
//    public static List DataSplit(String data){
//        List list = Collections.synchronizedList(new LinkedList());
//        String str = data.substring(11);
//        String[] arr = str.split(",");
//        for (int i = 0; i<arr.length;i++) {
//            list.add(Double.valueOf(arr[i]));
//        }
//        return list;
//    }
}
