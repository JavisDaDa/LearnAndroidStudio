package com.example.mikejia.appstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    List<Map<String, Object>> list;
    LayoutInflater inflater;//反射器

    public MyAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item, null);
        ImageView logo = view.findViewById(R.id.logo);
        TextView title = view.findViewById(R.id.title);
        TextView version = view.findViewById(R.id.version);
        TextView size = view.findViewById(R.id.size);

        Map map = list.get(position);
        logo.setImageResource((Integer) map.get(logo));
        title.setText((String) map.get(title));
        version.setText((String) map.get(version));
        size.setText((String) map.get(size));
        return view;
    }
}
