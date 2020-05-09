package com.example.jadwalpelajaranroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jadwalpelajaranroom.model.Schedule;
import com.example.jadwalpelajaranroom.repository.ScheduleRepository;
import com.example.jadwalpelajaranroom.viewmodel.ScheduleViewModel;

import java.util.Calendar;

public class InputActivity extends AppCompatActivity {

    private NumberPicker picker;;
    private EditText lecture;
    private EditText matkul;
    private EditText edtime;
    private Button picker_time;
    private ScheduleRepository repository;
    private ScheduleViewModel scheduleViewModel;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Save Data");
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        lecture = findViewById(R.id.ed_dosen);
        matkul = findViewById(R.id.ed_pelajaran);
        edtime = findViewById(R.id.time);
        picker_time = findViewById(R.id.btn_picker);
        picker = findViewById(R.id.number_priority);
        picker.setMinValue(1);
        picker.setMaxValue(7);
        picker.setDisplayedValues( new String[] { "Senin", "Selasa", "Rabu","Kamis", "Jumaat", "Sabtu","Minggu" } );

        Intent intent=getIntent();
        if(intent.hasExtra(DasboardActivity.EXTRA_ID)){
            setTitle("Edit Schedule");
            lecture.setText(intent.getStringExtra(DasboardActivity.TEACHER_NAME));
            matkul.setText(intent.getStringExtra(DasboardActivity.SCHEDULE));
            picker.setValue(intent.getIntExtra(DasboardActivity.DAY,1));
            edtime.setText(intent.getStringExtra(DasboardActivity.TIME));
        }else {
            setTitle("Add Schedule");
        }

        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        picker_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(InputActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtime.setText(hourOfDay + ":"+minute);
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(InputActivity.this));
                timePickerDialog.show();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void save() {
        String title = lecture.getText().toString();
        String desc  = matkul.getText().toString();
        String time =  edtime.getText().toString();
        int priority = picker.getValue();

        if(title.trim().isEmpty() || desc.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title  and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(DasboardActivity.DAY,priority);
        data.putExtra(DasboardActivity.SCHEDULE,desc);
        data.putExtra(DasboardActivity.TIME,time);
        data.putExtra(DasboardActivity.TEACHER_NAME,title);

        int id = getIntent().getIntExtra(DasboardActivity.EXTRA_ID,-1);
        if(id != -1){
            data.putExtra(DasboardActivity.EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();
    }
}
