package com.example.hy.myapplication;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题栏
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏

        setContentView(R.layout.main7);//加载布局文件
        //1.拿到listview对象
        ListView lv = (ListView) findViewById(R.id.lv_main);
        //2.数据源
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("logo",R.drawable.ye);
        map.put("title","叶子");
        map.put("version","版本：2.1.1");
        map.put("size","大小：80.9M");
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("logo",R.drawable.yuan);
        map.put("title","圆");
        map.put("version","版本：1.1.1");
        map.put("size","大小：10.9M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo",R.drawable.hong);
        map.put("title","娱乐");
        map.put("version","版本：2.1.0");
        map.put("size","大小：35.9M");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo",R.drawable.xin);
        map.put("title","心");
        map.put("version","版本：5.1.1");
        map.put("size","大小：90.9M");
        list.add(map);


        //3.设置适配器


//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                list,
//                R.layout.item,
//                new String[]{"logo","title","version","size"},
//                new int[]{R.id.logo,R.id.title,R.id.version,R.id.size}
//        );


        MyAdapter adapter = new MyAdapter(this);//this指的是activity
       //传数据
        adapter.setList(list);




        //4.关联适配器
        lv.setAdapter(adapter);


        //5监听
        lv.setOnItemClickListener(this);//用需要实现接口
        lv.setOnItemLongClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//        Toast.makeText(this,"点击"+position,Toast.LENGTH_SHORT).show();
        //跳转
        Intent intent = new Intent();//无参构造
        switch (position){
            case 0:
                intent.setClass(this,DetailActivity.class);
                break;
            case 1:
                intent.setClass(this,DetailActivity2.class);

        }
//        intent.setClass(this,DetailActivity.class);
//        Map<String,Object> itemMap =
//                (Map<String,Object>) parent.getItemAtPosition(position);
//        intent.putExtra("index",""+position);
//        intent.putExtra("title",""+itemMap.get("title"));

        startActivity(intent);



    }

    ArrayList<Integer> choice = new ArrayList<Integer>();

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//        Toast.makeText(this,"长按"+position,Toast.LENGTH_SHORT).show();
         final String[] arr = {"游泳","读书","足球","逛街","其他"};


        //实例化一个建造者模式
      new AlertDialog.Builder(this)

        //图标
       .setIcon(R.drawable.yuan)
        //标题
        .setTitle("消息提示")
        //消息
//        .setMessage("这是一个简单的提示")
        //按钮
         .setMultiChoiceItems(arr,
                 new boolean[]{false, false, false, false, false},
                 new DialogInterface.OnMultiChoiceClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                         if(isChecked){
                             choice.add(which);

                         }else {
                             choice.remove(which);
                         }

                         Toast.makeText(MainActivity.this,"用户的选择是："+choice.toString(),Toast.LENGTH_SHORT).show();

                     }
                 })
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                finish();
                Toast.makeText(MainActivity.this,"用户的决定是："+choice.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        .show();
        return true;//如果希望长按事件后，不再触发事件，则return true。



    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.yuan)
                .setTitle("确认对话框")
                .setMessage("您真的要退出程序吗？")
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                        System.exit(0);

                    }
                }).show();
    }
}
