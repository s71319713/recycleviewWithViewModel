package com.example.recycleviewwithviewmodel.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;


import com.example.recycleviewwithviewmodel.room.MyDataBase;
import com.example.recycleviewwithviewmodel.room.TextEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    MyDataBase myDataBase;
    MyViewModel myViewModel;

    public Repository(Context context){
        myDataBase = MyDataBase.getDatabaseInstance(context);
        myViewModel = ViewModelProviders.of((ScoreActivity)context).get(MyViewModel.class);
    }

    public void getAll(){
        myDataBase.myDao().queryTextEntity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TextEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<TextEntity> textEntities) {
                        ArrayList<String> strings = new ArrayList<>();
                        for(TextEntity textEntity:textEntities){
                            strings.add(textEntity.toStr());
                        }
                        myViewModel.getStringList().setValue(strings);
                        Log.d("ssss", "onSuccess: viewmodel更新成功~~");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    public void insert(String str){
        myDataBase.myDao().insert(new TextEntity(0,str)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getAll();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
