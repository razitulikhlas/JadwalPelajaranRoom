package com.example.jadwalpelajaranroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jadwalpelajaranroom.model.Schedule;
import com.example.jadwalpelajaranroom.repository.ScheduleRepository;

import java.util.List;

public class ScheduleViewModel extends AndroidViewModel {

    private ScheduleRepository reprository;
    private LiveData<List<Schedule>> allSchedule;


    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        reprository = new ScheduleRepository(application);
        allSchedule = reprository.getAll();
    }

    public void insert(Schedule schedule){
        reprository.insert(schedule);
    }

    public void update(Schedule schedule){
        reprository.update(schedule);
    }

    public void delete(Schedule schedule){
        reprository.delete(schedule);
    }

    public void deleteAllNotes(){
        reprository.deleteAllSchedule();
    }

    public LiveData<List<Schedule>> getAll(){
        return allSchedule;
    }
}
