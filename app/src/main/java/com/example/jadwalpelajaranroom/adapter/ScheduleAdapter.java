package com.example.jadwalpelajaranroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jadwalpelajaranroom.R;
import com.example.jadwalpelajaranroom.model.Schedule;

import java.util.List;

public class ScheduleAdapter extends ListAdapter<Schedule,ScheduleAdapter.ViewHolder> {


    private OnItemClickListener listener;

   public ScheduleAdapter(){
       super(DIFF_CALLBACK);
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule,parent,false);
        return new ViewHolder(view);
    }

    public Schedule getSchedule(int position){
        return getItem(position);

    }

    private static final DiffUtil.ItemCallback<Schedule> DIFF_CALLBACK = new DiffUtil.ItemCallback<Schedule>() {
        @Override
        public boolean areItemsTheSame(@NonNull Schedule oldItem, @NonNull Schedule newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Schedule oldItem, @NonNull Schedule newItem) {
            return oldItem.getCourse().equals(newItem.getCourse()) &&
                    oldItem.getTeacher_name().equals(newItem.getTeacher_name())&&
                    oldItem.getTime().equals(newItem.getTime())&&
                    oldItem.getPriority_schedule() == newItem.getPriority_schedule();
        }

    };

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = getItem(position);
        String name ="";
        if(schedule.getPriority_schedule() == 1){
            name = "senin";
        }else if(schedule.getPriority_schedule() == 2){
            name = "selasa";
        }else if(schedule.getPriority_schedule() == 3){
            name = "rabu";
        }else if(schedule.getPriority_schedule() == 4){
            name = "kamis";
        }else if(schedule.getPriority_schedule() == 5){
            name = "jum'at";
        }else if(schedule.getPriority_schedule() == 6){
            name = "sabtu";
        }else if(schedule.getPriority_schedule() == 7){
            name = "minggu";
        }
        holder.day.setText(name);
        holder.name_schedule.setText(schedule.getCourse());
        holder.name_teacher.setText(schedule.getTeacher_name());
        holder.time.setText(schedule.getTime());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_teacher;
        private TextView name_schedule;
        private TextView time;
        private TextView day;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_teacher = itemView.findViewById(R.id.tv_teacher_name);
            name_schedule = itemView.findViewById(R.id.tv_name_schedule);
            time = itemView.findViewById(R.id.tv_time);
            day = itemView.findViewById(R.id.tv_day);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Schedule schedule);
    }

    public void setOnItemClickListeners(OnItemClickListener listener) {
        this.listener = listener;

    }
}
