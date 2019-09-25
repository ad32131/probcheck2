package com.example.probcheck;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;

public class accident_notice extends AppCompatActivity implements View.OnClickListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_notice);


    }

    @Override
    public void onClick(View view){

        DatabaseReference mDatabase;
        robotsend userdata;

        DatabaseReference nRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference nfoodinfoRef = nRootRef.child("command").child("opener").child("userName");
        switch (view.getId()) {
            case R.id.buttonyes:
                mDatabase = FirebaseDatabase.getInstance().getReference();
                userdata = new robotsend("GO");
                mDatabase.child("command").child("mobile").setValue(userdata);
                Toast.makeText(getApplicationContext(), "안전삼각대로봇이\n호출되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, InjuriesOccur.class);
                startActivity(intent);
                nfoodinfoRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DatabaseReference mDatabase;
                        robotsend userdata;
                        String text = dataSnapshot.getValue(String.class);
                        if(text.equals("YAB")){
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend("DOWN");
                            mDatabase.child("command").child("cradle").setValue(userdata);


                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case R.id.buttonno:
                mDatabase = FirebaseDatabase.getInstance().getReference();
                userdata = new robotsend("Not Accident");
                mDatabase.child("command").child("mobile").setValue(userdata);
                this.finish();
                Toast.makeText(getApplicationContext(), "안전운전하세요.", Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
