package com.example.mikejia.drawapplication;
//加工字符串，返回float数组
public class DataProcessing {
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
//    public static int[] DataSplit(){
//        String str = data.substring(11);
//        String[] arr = str.split(",");
//        int[] result = new int[arr.length];
//        for (int i = 0; i<result.length;i++) {
//            result[i] = Integer.valueOf(arr[i]);
//        }
//        return result;
//    }
}
