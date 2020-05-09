package com.example.jadwalpelajaranroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Schedule")
public class Schedule {

    @PrimaryKey(autoGenerate = true)
    private int id;
    public int priority_schedule;
    @ColumnInfo(name = "course")
    public String course;
    @ColumnInfo(name = "teacher_name")
    public String teacher_name;
    @ColumnInfo(name = "time")
    public String time;
    @ColumnInfo(name = "databaru")
    public String databaru;




    public Schedule() {
    }

    public Schedule(int priority_schedule, String course, String teacher_name, String time) {
        this.priority_schedule = priority_schedule;
        this.course = course;
        this.teacher_name = teacher_name;
        this.time = time;

    }


    public String getDatabaru() {
        return databaru;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPriority_schedule() {
        return priority_schedule;
    }

    public String getCourse() {
        return course;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getTime() {
        return time;
    }
}
