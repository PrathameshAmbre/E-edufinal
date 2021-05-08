package com.example.eedu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    TextView first_name,email,myLearning,wishList,editProfile,help,logout;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    String firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        first_name=findViewById(R.id.name);
        email=findViewById(R.id.email_id);
        myLearning=findViewById(R.id.my_learning);
        wishList=findViewById(R.id.wishlist);
        editProfile=findViewById(R.id.edit_profile);
        help=findViewById(R.id.help);
        logout=findViewById(R.id.log_out);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser().getUid();
        //firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading user data");
        progressDialog.setMessage("Please wait while we load the user data");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        myLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent=new Intent(ProfileActivity.this,MyCourseActivity.class);
                startActivity(editProfileIntent);
            }
        });

        wishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent=new Intent(ProfileActivity.this,FavouriteActivity.class);
                startActivity(editProfileIntent);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent=new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(editProfileIntent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent=new Intent(ProfileActivity.this,ContactUsActivity.class);
                startActivity(editProfileIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ------------------to be add-------------------
            }
        });

        LoadInformation();

    }

    public void LoadInformation(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue() == null){
                    Toast.makeText(ProfileActivity.this,"Update your deatails...",Toast.LENGTH_LONG).show();


                }
                else {

                    String user_name = snapshot.child("name").getValue().toString();
                    String user_email = snapshot.child("email").getValue().toString();

                    first_name.setText(user_name);
                    email.setText(user_email);

                    progressDialog.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}