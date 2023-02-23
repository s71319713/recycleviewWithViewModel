package com.example.recycleviewwithviewmodel.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TextEntity.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "text_db";

    private static MyDataBase databaseInstance;

    public static synchronized MyDataBase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase.class, DATABASE_NAME).build();
        }
        return databaseInstance;
    }

    public abstract MyDao myDao();
}
