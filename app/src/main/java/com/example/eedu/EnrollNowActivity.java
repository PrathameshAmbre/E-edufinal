package com.example.eedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnrollNowActivity extends AppCompatActivity {
    Button btn_enroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_now);

        btn_enroll=findViewById(R.id.enroll_now_btn);
        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EnrollNowActivity.this,CoursePageActivity.class);
                startActivity(intent);
            }
        });
    }
}