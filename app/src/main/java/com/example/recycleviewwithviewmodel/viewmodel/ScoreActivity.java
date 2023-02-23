package com.example.recycleviewwithviewmodel.viewmodel;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recycleviewwithviewmodel.R;
import com.example.recycleviewwithviewmodel.databinding.ActivityScoreBinding;


public class ScoreActivity extends AppCompatActivity {
    ActivityScoreBinding binding;
    MyViewModel viewModel;
    MyRecycleViewAdapter myRecycleViewAdapter;
    Repository repository;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_score);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        repository = new Repository(this);
        binding.setData(viewModel);
        binding.setRepo(repository);
        binding.setLifecycleOwner(this);
        initView();


    }

    private void initView() {
        myRecycleViewAdapter = new MyRecycleViewAdapter(this,binding.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recycleview.setLayoutManager(linearLayoutManager);
        binding.recycleview.setAdapter(myRecycleViewAdapter);
    }
}
