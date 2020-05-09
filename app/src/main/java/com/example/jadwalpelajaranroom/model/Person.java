package com.example.jadwalpelajaranroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name_person")
    String name;
    int age;
    String skin;

    public Person() {
    }

    public Person(String name, int age, String skin) {
        this.name = name;
        this.age = age;
        this.skin = skin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSkin() {
        return skin;
    }
}
