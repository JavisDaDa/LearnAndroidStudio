package com.example.hy.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created By HY on 2019/4/12
 **/
public class MyAdapter extends BaseAdapter {
//数据集合
    List<Map<String,Object>> list;

    //反射器
    LayoutInflater inflater;

    //在构造器里面对反射器做初始化工作


    public MyAdapter(Context context) {//context是上下文
        this.inflater = LayoutInflater.from(context);//初始化反射器
    }


    //传入list集合对象的方法


    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }





//先实现简单的前3个方法
    @Override
    public int getCount() {//获取listview的行数(系统显示这个界面首先就要调用这个方法)
        return list.size();
    }



    @Override
    public Object getItem(int position) {//这个项的对象，position是下标
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {//返回当前项的id
        return position;
    }



/*
系统在渲染第一行的时候，调用getViewh会传入position  0,
返回是啥，就显示啥：第一行显示的内容是啥，关键在于return啥样的对象
 */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView==null){

            convertView=inflater.inflate(R.layout.item,null);
            holder = new ViewHolder();
            holder.logo = (ImageView)  convertView.findViewById(R.id.logo);
            holder.title = (TextView)  convertView.findViewById(R.id.title);
            holder.version = (TextView) convertView.findViewById(R.id.version);
            holder.size = (TextView) convertView.findViewById(R.id.size);
            holder.btn = (Button) convertView.findViewById(R.id.btn);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
//拿到一个view视图对象（从布局文件中发反射出来）
//        View view = inflater.inflate(R.layout.item,null);//用发射器，把xml的一个文本文件反射射成一个真实的view对象（反射器的作用）
//开始填空

        Map map = list.get(position);
        holder.logo.setImageResource((Integer) map.get("logo"));
        holder.title.setText((String)map.get("title"));
        holder.version.setText((String)map.get("version"));
        holder.size.setText((String)map.get("size"));
        

        return  convertView;
    }

    public class ViewHolder{
        ImageView logo;
        TextView title;
        TextView version;
        TextView size;

        Button btn;
    }
}
