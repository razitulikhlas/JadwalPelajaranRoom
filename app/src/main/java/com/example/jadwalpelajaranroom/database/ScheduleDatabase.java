package com.example.jadwalpelajaranroom.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

;
import com.example.jadwalpelajaranroom.model.Person;
import com.example.jadwalpelajaranroom.model.Schedule;

@Database(entities = {Schedule.class, Person.class},version = 2)
public abstract class ScheduleDatabase extends RoomDatabase {

    private static ScheduleDatabase instance;
    public abstract ScheduleDao scheduleDao();
    public static final String DATABASE_NAME = "db_schedule";

    public static synchronized ScheduleDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ScheduleDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsytask(instance).execute();
        }
    };


    private static class PopulateDbAsytask extends AsyncTask<Void,Void,Void> {

        private ScheduleDao scheduleDao;

        private PopulateDbAsytask(ScheduleDatabase db){
            scheduleDao = db.scheduleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            scheduleDao.insert(new Schedule(1,"Bahasa inggris","Hidayatul sidiq","10:00 - 12:00"));
            scheduleDao.insert(new Schedule(2,"Bahasa indonesia","Hidayatul sidiq","10:00 - 12:00"));
            scheduleDao.insert(new Schedule(1,"Bahasa inggris","Hidayatul sidiq","10:00 - 12:00"));
            return null;
        }
    }

}

