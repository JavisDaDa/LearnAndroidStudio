package com.example.hy.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created By HY on 2019/4/13
 **/
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏
        setContentView(R.layout.main8);
        //初始化控件

//        String index = getIntent().getStringExtra("index");
//        String title = getIntent().getStringExtra("title");
        TextView info = (TextView) findViewById(R.id.info);
//        info.setText("编号："+index+""+title);
    }
}
