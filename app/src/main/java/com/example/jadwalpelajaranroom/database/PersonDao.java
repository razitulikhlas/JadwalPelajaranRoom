package com.example.jadwalpelajaranroom.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jadwalpelajaranroom.model.Person;
import com.example.jadwalpelajaranroom.model.Schedule;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    void insert(Person person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("DELETE FROM person")
    void deleteAll();

    @Query("SELECT * FROM person ")
    LiveData<List<Schedule>> getAll();
}
