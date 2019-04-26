package com.example.mikejia.appstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener { //implements View.OnClickListener {

    //声明控件对象
    Button btn_show, btn_quit;
    EditText ed_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        setContentView(R.layout.list_view1);//加载新的布局

        //1.拿到listview对象
        ListView lv = findViewById(R.id.lv_main);

//        //2.准备数据源
//        String[] data = {"初识Android", "开发搭建环境", "基础控件①", "基础控件②", "线性布局"};
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("logo", R.drawable.android);
        map.put("title", "安卓");
        map.put("version", "版本3.3.4");
        map.put("size", "大小：3.4M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.eclipse);
        map.put("title", "eclipse");
        map.put("version", "版本3.4.4");
        map.put("size", "大小：34M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.matlab);
        map.put("title", "matlab");
        map.put("version", "版本3.4");
        map.put("size", "大小：3.4G");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.python);
        map.put("title", "Python");
        map.put("version", "版本3.7");
        map.put("size", "大小：123M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.android);
        map.put("title", "安卓");
        map.put("version", "版本3.3.4");
        map.put("size", "大小：3.4M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.eclipse);
        map.put("title", "eclipse");
        map.put("version", "版本3.4.4");
        map.put("size", "大小：34M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.matlab);
        map.put("title", "matlab");
        map.put("version", "版本3.4");
        map.put("size", "大小：3.4G");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.python);
        map.put("title", "Python");
        map.put("version", "版本3.7");
        map.put("size", "大小：123M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.android);
        map.put("title", "安卓");
        map.put("version", "版本3.3.4");
        map.put("size", "大小：3.4M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.eclipse);
        map.put("title", "eclipse");
        map.put("version", "版本3.4.4");
        map.put("size", "大小：34M");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.matlab);
        map.put("title", "matlab");
        map.put("version", "版本3.4");
        map.put("size", "大小：3.4G");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.drawable.python);
        map.put("title", "Python");
        map.put("version", "版本3.7");
        map.put("size", "大小：123M");
        list.add(map);

        //3.设置适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                data
//        );

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.item,
                new String[]{"logo","title", "version", "size"},
                new int[]{R.id.logo, R.id.title, R.id.version, R.id.size}
        );

//        MyAdapter adapter = new MyAdapter(this);
//        adapter.setList(list);

        //4.关联适配器
        lv.setAdapter(adapter);

        //5.
        lv.setOnItemClickListener(this);
//        //获取TextView对象
//        TextView tv_show = findViewById(R.id.tv_show);
//        //获取文本内容
//        String text = tv_show.getText().toString();
//
//        Log.i("MainActivity",text);
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//
//        //设定文本内容
//        tv_show.setText("nihao");
//        tv_show.setTextColor(getResources().getColor(R.color.colorAccent));
        /*
        监听按钮点击事件
        步骤
        1.获取控件对象
        2.注册监听器
        3.编写响应代码
         */
//        btn_show = findViewById(R.id.btn_show);
//        ed_search = findViewById(R.id.ed_search);
//        btn_quit = findViewById(R.id.btn_quit);
//
////        //注册监听器
////        btn_show.setOnClickListener(new View.OnClickListener() {            //匿名内部类
////            @Override
////            public void onClick(View v) {
////                //响应
////                String text = "您点击了按钮";
////                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
////                Log.i("MainActivity", text);
////            }
////        });
//
//        btn_show.setOnClickListener(this);
//        btn_quit.setOnClickListener(this);
//
//    }
//
////    public void btnClick(View v){
////
////    }
//    @Override
//    public void onClick(View v) {       //实现接口
//        String text = ed_search.getText().toString();
//        //响应
//        switch (v.getId()){
//            case R.id.btn_show:
//                text += "按钮1";
//                break;
//            case R.id.btn_quit:
//                text += "按钮2";
//                break;
//        }
//
//        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//        Log.i("MainActivity", text);
//    }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "点击" + position, Toast.LENGTH_LONG);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "点击" + position, Toast.LENGTH_LONG);
//    }
}
