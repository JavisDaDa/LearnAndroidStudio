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

public class ChannelRightFragment extends Fragment {
    MainActivity activity = (MainActivity) getActivity();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.channel_right_fragment, container, false);
        RecyclerView channelRecyclerView = view.findViewById(R.id.channel_fragment_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        channelRecyclerView.setLayoutManager(layoutManager);
        ChannelAdapter adapter = new ChannelAdapter(getChannelFunction());
        channelRecyclerView.setAdapter(adapter);
        return view;
    }

    //设置测量的功能
    private List<Function> getChannelFunction() {
        List<Function> functionList = new ArrayList<>();
        for (int i = 1; i <=4; i++){
            Function channel = new Function();
            channel.setFunction("通道" + i +": 直流耦合");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 交流耦合");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 1M阻抗");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 50阻抗");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 限制带宽");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 不限制带宽");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 探头单位：V");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 探头单位：A");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 探头：分贝");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 探头：比率");
            functionList.add(channel);

            channel = new Function();
            channel.setFunction("通道" + i +": 时滞");
            functionList.add(channel);
        }
        return functionList;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
        private List<Function> mFunctionList;

        class ViewHolder extends RecyclerView.ViewHolder {

            Button measureFunction;

            public ViewHolder(View view) {
                super(view);
                measureFunction = view.findViewById(R.id.btn_functions);
            }
        }

            public ChannelAdapter(List<Function> functionList) {
            mFunctionList = functionList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.function_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Function function = mFunctionList.get(holder.getAdapterPosition());
                    Log.i("jyd", function.getFunction());
//                    Toast.makeText(activity, "您点击了"+measure.getFunction().toString(), Toast.LENGTH_LONG).show();
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Function function = mFunctionList.get(position);
            holder.measureFunction.setText(function.getFunction());
        }

        @Override
        public int getItemCount() {
            return mFunctionList.size();
        }
    }
}
