package com.example.jadwalpelajaranroom.repository;

import android.app.Application;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.jadwalpelajaranroom.database.ScheduleDao;
import com.example.jadwalpelajaranroom.database.ScheduleDatabase;
import com.example.jadwalpelajaranroom.model.Schedule;

import java.util.List;

public class ScheduleRepository {
    private ScheduleDao scheduleDao;
    private LiveData<List<Schedule>> scheduleList;

    public ScheduleRepository(Application application) {
        ScheduleDatabase database=ScheduleDatabase.getInstance(application);
        scheduleDao = database.scheduleDao();
        scheduleList = scheduleDao.getAll();
    }

//    public void insert(Schedule schedule){
//        scheduleDao.insert(schedule);
//    }
//
//    public void update(Schedule schedule){
//        scheduleDao.update(schedule);
//
//    }
//
//    public void delete(Schedule schedule){
//        scheduleDao.delete(schedule);
//
//    }

    public void deleteAllSchedule(){
        scheduleDao.deleteAll();
    }

    public LiveData<List<Schedule>> getAll() {
        return scheduleList;
    }

    public void insert(Schedule schedule){
        new InsertNoteAsyntask(scheduleDao).execute(schedule);

    }

    public void update(Schedule schedule){
        new UpdateNoteAsyntask(scheduleDao).execute(schedule);

    }

    public void delete(Schedule schedule){
        new DeleteNoteAsyntask(scheduleDao).execute(schedule);

    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsyntask(scheduleDao).execute();

    }



    private static class InsertNoteAsyntask extends AsyncTask<Schedule,Void,Void> {

        private ScheduleDao scheduleDao;

        private InsertNoteAsyntask(ScheduleDao scheduleDao) {
            this.scheduleDao = scheduleDao;
        }

        @Override
        protected Void doInBackground(Schedule... schedules) {
            scheduleDao.insert(schedules[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyntask extends AsyncTask<Schedule,Void,Void>{

        private ScheduleDao scheduleDao;

        private UpdateNoteAsyntask(ScheduleDao scheduleDao) {
            this.scheduleDao = scheduleDao;
        }

        @Override
        protected Void doInBackground(Schedule... schedules) {
            scheduleDao.update(schedules[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyntask extends AsyncTask<Schedule,Void,Void>{

        private ScheduleDao scheduleDao;

        private DeleteNoteAsyntask(ScheduleDao scheduleDao) {
            this.scheduleDao = scheduleDao;
        }

        @Override
        protected Void doInBackground(Schedule...schedules) {
            scheduleDao.delete(schedules[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyntask extends AsyncTask<Void,Void,Void>{

        private ScheduleDao scheduleDao;

        private DeleteAllNoteAsyntask(ScheduleDao scheduleDao) {
            this.scheduleDao = scheduleDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            scheduleDao.deleteAll();
            return null;
        }
    }
}
