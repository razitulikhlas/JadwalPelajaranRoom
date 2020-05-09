package com.example.jadwalpelajaranroom.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.jadwalpelajaranroom.model.Schedule;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert
    void insert(Schedule schedule);

    @Update
    void update(Schedule schedule);

    @Delete
    void delete(Schedule schedule);

    @Query("DELETE FROM schedule")
    void deleteAll();

    @Query("SELECT * FROM schedule ORDER BY priority_schedule ASC")
    LiveData<List<Schedule>> getAll();


}
