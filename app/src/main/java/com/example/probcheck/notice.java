package com.example.probcheck;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class notice extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);



    }

    @Override
    public void onClick(View view){
        DatabaseReference mDatabase;
        robotsend userdata;
        File serv = new File("/data/data/com.example.probcheck/notice.ini");
        switch (view.getId()) {
            case R.id.buttonyes:
                Intent intent=new Intent(this, accident_notice.class);
                startActivity(intent);
                this.finish();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                userdata = new robotsend("break");
                mDatabase.child("command").child("BREAK").setValue(userdata);

                break;
            case R.id.buttonno:


                try{
            Log.e("error working!!","error working!!");
            serv.createNewFile();
            FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/notice.ini");
            fop.write("0".getBytes());
            fop.close();
        }catch (Exception e) {
            Log.e("FILE", "/data/data/com.example.probcheck/notice.ini");
            Log.e("FILE",e.toString());
        }finally {
            Log.e("FILE","ERR");
        }
        Toast.makeText (this,"사고발생 오작동", Toast.LENGTH_SHORT).show();
        this.finish();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                userdata = new robotsend("000");
                mDatabase.child("command").child("BREAK").setValue(userdata);

        break;

    }
    }


}
