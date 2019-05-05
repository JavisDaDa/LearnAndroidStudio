package com.example.mikejia.uidesigner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TriggerRightFragment extends Fragment{
    MainActivity activity = (MainActivity) getActivity();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trigger_right_fragment, container, false);
        RecyclerView TriggerRecyclerView = view.findViewById(R.id.trigger_fragment_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        TriggerRecyclerView.setLayoutManager(layoutManager);
        TriggerAdapter adapter = new TriggerAdapter(getTriggerFunction());
        TriggerRecyclerView.setAdapter(adapter);
        return view;
    }

    //设置测量的功能
    private List<Function> getTriggerFunction(){
        List<Function> triggerList = new ArrayList<>();
        Function trigger = new Function();
        trigger.setFunction("Edge: 1");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("Edge: 2");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("Edge: 上升沿");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("Edge: 下降沿");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("Edge: 交变沿");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("Edge: 任一沿");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("自动触发");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("标准触发");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("直流耦合");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("交流耦合");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("低频抑制耦合");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("开启噪声抑制");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("关闭噪声抑制");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("开启高频抑制");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("关闭高频抑制");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("外部单位：V");
        triggerList.add(trigger);


        trigger = new Function();
        trigger.setFunction("外部单位：A");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("探头：分贝");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("探头：比率");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("1M阻抗");
        triggerList.add(trigger);

        trigger = new Function();
        trigger.setFunction("50阻抗");
        triggerList.add(trigger);
        return triggerList;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class TriggerAdapter extends RecyclerView.Adapter<TriggerAdapter.ViewHolder>{
        private List<Function> mTriggerList;
        class  ViewHolder extends RecyclerView.ViewHolder{

            Button measureFunction;
            public ViewHolder(View view){
                super(view);
                measureFunction = view.findViewById(R.id.btn_functions);
            }
        }

        public TriggerAdapter(List<Function> TriggerList){
            mTriggerList = TriggerList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.function_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Function measure = mTriggerList.get(holder.getAdapterPosition());
                    Log.i("jyd", measure.getFunction());
//                    Toast.makeText(activity, "您点击了"+measure.getFunction().toString(), Toast.LENGTH_LONG).show();
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Function measure = mTriggerList.get(position);
            holder.measureFunction.setText(measure.getFunction());
        }

        @Override
        public int getItemCount() {
            return mTriggerList.size();
        }
    }



}
