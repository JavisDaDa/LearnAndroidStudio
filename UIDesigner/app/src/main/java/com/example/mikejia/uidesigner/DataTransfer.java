package com.example.mikejia.uidesigner;

import android.util.Log;

public class DataTransfer {
    public static String mShow = MainActivity.data.toString();
    private static DataProcessing df;
    public static float[] getData(){
        if (mShow != null){
            Log.i("jyd_dataTransfer", mShow);
            df = new DataProcessing(mShow);
            mShow = null;
        }
        return df.DataSplit();
    }
}
