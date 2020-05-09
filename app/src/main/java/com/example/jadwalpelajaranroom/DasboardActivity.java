package com.example.jadwalpelajaranroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jadwalpelajaranroom.adapter.ScheduleAdapter;

import com.example.jadwalpelajaranroom.model.Schedule;

import com.example.jadwalpelajaranroom.viewmodel.ScheduleViewModel;

import java.util.List;

public class DasboardActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SAVE = 004;
    private static final int EDIT_SCHEDULE_REQUEST = 0045;

    private ScheduleAdapter adapter;
    private RecyclerView recyclerView;
    ScheduleViewModel scheduleViewModel;

    public final static String TEACHER_NAME="com.example.jadwalpelajaranroom.TEACHER";
    public final static String DAY="com.example.jadwalpelajaranroom.DAY";
    public final static String TIME="com.example.jadwalpelajaranroom.TIME";
    public final static String SCHEDULE="com.example.jadwalpelajaranroom.SCHEDULE";
    public final static String EXTRA_ID="com.example.jadwalpelajaranroom.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        adapter = new ScheduleAdapter();
        scheduleViewModel.getAll().observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                adapter.submitList(schedules);
                recyclerView.setAdapter(adapter);
            }
        });



        findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DasboardActivity.this,InputActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SAVE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                scheduleViewModel.delete(adapter.getSchedule(viewHolder.getAdapterPosition()));
                Toast.makeText(DasboardActivity.this, "Schedule succes delete ^_^", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListeners(new ScheduleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Schedule schedule) {
                Intent intent = new Intent(DasboardActivity.this, InputActivity.class);
                intent.putExtra(EXTRA_ID, schedule.getId());
                intent.putExtra(TEACHER_NAME,schedule.teacher_name);
                intent.putExtra(SCHEDULE,schedule.course);
                intent.putExtra(DAY, schedule.priority_schedule);
                intent.putExtra(TIME, schedule.time);
                startActivityForResult(intent, EDIT_SCHEDULE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SAVE && resultCode == RESULT_OK){
            if(data != null){
                String teacher_name = data.getStringExtra(TEACHER_NAME);
                int day = data.getIntExtra(DAY,0);
                String time = data.getStringExtra(TIME);
                String schedule = data.getStringExtra(SCHEDULE);
                Schedule schedule1= new Schedule(day,schedule,teacher_name,time);
                scheduleViewModel.insert(schedule1);
            }
            Toast.makeText(this, "data success save", Toast.LENGTH_SHORT).show();
        }else if (requestCode == EDIT_SCHEDULE_REQUEST && resultCode == RESULT_OK) {
            if(data != null){
                int id = data.getIntExtra(EXTRA_ID,-1);
                if(id == -1 ){
                    Toast.makeText(this, "Note Can't be update", Toast.LENGTH_SHORT).show();
                    return;
                }
                String teacher_name = data.getStringExtra(TEACHER_NAME);
                int day = data.getIntExtra(DAY,0);
                String time = data.getStringExtra(TIME);
                String schedule = data.getStringExtra(SCHEDULE);
                Schedule schedule1= new Schedule(day,schedule,teacher_name,time);
                schedule1.setId(id);
                scheduleViewModel.update(schedule1);
                Toast.makeText(this, "Note Succes Update ^_^", Toast.LENGTH_SHORT).show();
            }else{
            Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
        }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_dasboard,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_schedule:
                scheduleViewModel.deleteAllNotes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
