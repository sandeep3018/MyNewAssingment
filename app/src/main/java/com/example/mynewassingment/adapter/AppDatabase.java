package com.example.mynewassingment.adapter;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Register.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
