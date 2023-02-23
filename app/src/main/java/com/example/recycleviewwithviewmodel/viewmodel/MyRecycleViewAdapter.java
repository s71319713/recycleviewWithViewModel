package com.example.recycleviewwithviewmodel.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recycleviewwithviewmodel.databinding.ItemViewBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    ItemViewBinding binding;
    MyViewModel myViewModel;
    ArrayList<String> stringArrayList=new ArrayList<>();
    Context context;

    public MyRecycleViewAdapter(Context context, RecyclerView recycleview){
        this.context = context;

        myViewModel = ViewModelProviders.of((ScoreActivity)context).get(MyViewModel.class);
        myViewModel.stringList.observe((ScoreActivity)context, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                stringArrayList = strings;
                Log.d("nnn", "now list: "+ Arrays.toString(strings.toArray()));
                notifyDataSetChanged();
                recycleview.scrollToPosition(stringArrayList.size()-1);


            }
        });
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemViewBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("nnn", "position: " + position + " - " + stringArrayList.get(position));
        holder.dataBindView(stringArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ItemViewBinding binding;
        public MyViewHolder(@NonNull ItemViewBinding binding) {
            super(binding.getRoot());
            //一定要拿傳過來的binding,我猜是因為拿全局的binding是整個recycleview的不是單個item的???
            this.binding = binding;


        }

        public void dataBindView(String str){
            Log.d("nnn", "set: " + getAdapterPosition() + " 為 " + stringArrayList.get(getAdapterPosition()));
            binding.textitem.setText(str);
        }


    }
}
