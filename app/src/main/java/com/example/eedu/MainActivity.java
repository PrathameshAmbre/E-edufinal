package com.example.eedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.eedu.CourseDatabase.CourseAdapterClass;
import com.example.eedu.courseDetails.categoryAdapter;
import com.example.eedu.courseDetails.categoryHelperClass;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    TextView display_name;
    DatabaseReference databaseReference;
    String firebaseUser;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        display_name=findViewById(R.id.home_name);
        recyclerView=findViewById(R.id.recyclerview_category);
        RecyclerView();

        setSupportActionBar(toolbar);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        mauth=FirebaseAuth.getInstance();
        firebaseUser= mauth.getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser);

        LoadInfo();

    }

    public void LoadInfo(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue() == null){
                    Toast.makeText(MainActivity.this,"Update your deatails...",Toast.LENGTH_LONG).show();
                }
                else {

                    String user_name = snapshot.child("name").getValue().toString();
                    //String user_surname = snapshot.child("surname").getValue().toString();
                    display_name.setText("Welcome Back "+user_name+"!");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void RecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        categoryHelperClass[] helperClasses=new categoryHelperClass[]{
                new categoryHelperClass(R.drawable.learn,"Big Data Analytics"),
                new categoryHelperClass(R.drawable.login3,"Python Programing"),
                new categoryHelperClass(R.drawable.forgot_password,"Aws Cloud"),
                new categoryHelperClass(R.drawable.login1,"Data structure"),
                new categoryHelperClass(R.drawable.login2,"Ethical Hacking"),
                new categoryHelperClass(R.drawable.login3,"Cyber Security"),
                new categoryHelperClass(R.drawable.bulb,"Machine Learning")
        };

        categoryAdapter adapter=new categoryAdapter(helperClasses,MainActivity.this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                break;
            case R.id.mycourse:
                Intent mycourse_intent=new Intent(MainActivity.this,EnrollNowActivity.class);
                startActivity(mycourse_intent);
                break;
            case R.id.favourite:
                Intent favourite_intent=new Intent(MainActivity.this,CourseListTopicwiseActivity.class);
                startActivity(favourite_intent);
                break;
            case R.id.login:
                Intent login_intent=new Intent(MainActivity.this,LogIn2.class);
                startActivity(login_intent);
                break;
            case R.id.profile:
                Intent profile_intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(profile_intent);
                break;
            case R.id.rate:
                Intent rateus_intent=new Intent(MainActivity.this,RateUsActivity.class);
                startActivity(rateus_intent);
                break;
            case R.id.contact:
                Intent contactus_intent=new Intent(MainActivity.this,ContactUsActivity.class);
                startActivity(contactus_intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}