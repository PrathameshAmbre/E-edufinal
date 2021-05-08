package com.example.eedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.eedu.CourseDatabase.CourseAdapterClass;
import com.example.eedu.CourseDatabase.courseData;

public class CourseListTopicwiseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_topicwise);

        RecyclerView recyclerView=findViewById(R.id.courseList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseData[] courseData=new courseData[]{
                new courseData("Java Programming for complete beginner","Become a confident industry ready java developer",R.drawable.java_logo),
                new courseData("Python Programming for complete beginner","Become a confident industry ready python developer",R.drawable.java_logo),
                new courseData("Java Programming for complete beginner","Become a confident industry ready java developer",R.drawable.java_logo)

        };

        CourseAdapterClass courseAdapterClass=new CourseAdapterClass(courseData,CourseListTopicwiseActivity.this);
        recyclerView.setAdapter(courseAdapterClass);
    }
}