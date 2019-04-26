package com.example.mikejia.uidesigner;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MeasureRightFragment extends Fragment{
    MainActivity activity = (MainActivity) getActivity();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.measure_right_fragment, container, false);
        RecyclerView measureRecyclerView = view.findViewById(R.id.measure_fragment_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        measureRecyclerView.setLayoutManager(layoutManager);
        MeasureAdapter adapter = new MeasureAdapter(getMeasureFunction());
        measureRecyclerView.setAdapter(adapter);
        return view;
    }

    //设置测量的功能
    private List<Function> getMeasureFunction(){
        List<Function> measureList = new ArrayList<>();
        Function measure = new Function();
        measure.setFunction("源1");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("源2");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("幅度");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("平均值");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("低值");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("计数");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("延迟时间");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("占空比");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("下降时间");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("频率");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("最大电平");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("最小电平");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("峰峰值");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("周期");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("上升时间");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("设置阈值");
        measureList.add(measure);


        measure = new Function();
        measure.setFunction("清除测量");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("统计信息开启");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("统计信息关闭");
        measureList.add(measure);

        measure = new Function();
        measure.setFunction("复位统计信息");
        measureList.add(measure);
        return measureList;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class MeasureAdapter extends RecyclerView.Adapter<MeasureAdapter.ViewHolder>{
        private List<Function> mMeasureList;
        class  ViewHolder extends RecyclerView.ViewHolder{

            Button measureFunction;
            public ViewHolder(View view){
                super(view);
                measureFunction = view.findViewById(R.id.btn_functions);
            }
        }

        public MeasureAdapter(List<Function> MeasureList){
            mMeasureList = MeasureList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.function_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Function measure = mMeasureList.get(holder.getAdapterPosition());
                    Log.i("jyd", measure.getFunction());
//                    Toast.makeText(activity, "您点击了"+measure.getFunction().toString(), Toast.LENGTH_LONG).show();
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Function measure = mMeasureList.get(position);
            holder.measureFunction.setText(measure.getFunction());
        }

        @Override
        public int getItemCount() {
            return mMeasureList.size();
        }
    }



}
