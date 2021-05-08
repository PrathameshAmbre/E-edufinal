package com.example.eedu.CourseDatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eedu.CourseListTopicwiseActivity;
import com.example.eedu.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapterClass extends RecyclerView.Adapter<CourseAdapterClass.ViewHolder> {

    courseData[] courseData;
    Context context;

    public CourseAdapterClass(courseData[] courseData, CourseListTopicwiseActivity activity){
        this.courseData=courseData;
        this.context=activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.course_list_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final courseData courseDataList=courseData[position];
        holder.courseName.setText(courseDataList.getCourseName());
        holder.courseDescription.setText(courseDataList.getCourseDescription());
        holder.courseImage.setImageResource(courseDataList.getCourseImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,courseDataList.getCourseName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView courseName,courseDescription;
        ImageView courseImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName=itemView.findViewById(R.id.courseName_courseList);
            courseDescription=itemView.findViewById(R.id.courseDescription_courseList);
            courseImage=itemView.findViewById(R.id.imageview_courseList);
        }
    }
}

