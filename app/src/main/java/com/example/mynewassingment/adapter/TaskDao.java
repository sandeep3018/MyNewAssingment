package com.example.mynewassingment.adapter;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM register")
    List<Register> getAll();

    @Insert
    void insert(Register register);

    @Delete
    void delete(Register register);

    @Update
    void update(Register register);


}
