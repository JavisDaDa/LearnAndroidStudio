package com.example.mikejia.oscilloscopereprogramming;

public class DataTransfer {
    public static String mShow = null;
    private static DataProcessing df;
    public static float[] getData(){
        if (mShow != null){
            df = new DataProcessing(mShow);
            mShow = null;
        }
        return df.DataSplit();
    }
}
