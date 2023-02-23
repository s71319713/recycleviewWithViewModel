package com.example.recycleviewwithviewmodel.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MyDao {

    @Insert
    public Completable insert(TextEntity textEntity);


    @Query("SELECt * FROM text_table")
    public Single<List<TextEntity>> queryTextEntity();

}
