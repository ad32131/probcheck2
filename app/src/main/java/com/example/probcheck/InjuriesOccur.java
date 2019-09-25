package com.example.probcheck;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InjuriesOccur extends AppCompatActivity implements View.OnClickListener {
    static double longitude;   //경도
    static double latitude;   //위도
    static double altitude;   //고도
    static float accuracy;    //정확도
    static String provider;   //위치제공자
    private GpsTracker gpsTracker;
    private static final int PERMISSIONS_REQUEST_READ_SMS = 100;
    String phonenumber = "";
    String phonenumber2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injuries_occur);

        gpsTracker = new GpsTracker(this);

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();
        Log.e("pos", "!!!!" + latitude + "!!!!" + longitude);


    }

    @Override
    public void onClick(View view) {
        DatabaseReference mDatabase;
        robotsend userdata;
        File serv = new File("/data/data/com.example.probcheck/notice.ini");
        SmsManager smsManager = SmsManager.getDefault();



        switch (view.getId()) {
            case R.id.buttonyes:
                Log.e("robot call", "robot call");
                /*Accident handling*/

                //this function is write firebase database for send robots
                mDatabase = FirebaseDatabase.getInstance().getReference();
                userdata = new robotsend("GO");
                mDatabase.child("command").child("mobile").setValue(userdata);

                gpsTracker = new GpsTracker(this);

                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();

                File serv2 = new File("/data/data/com.example.probcheck/tel_setting.ini");
                if (serv2.exists() == true) {
                    try {
                        byte[] inputtext2 = new byte[30];
                        FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/tel_setting.ini");
                        fip.read(inputtext2, 0, 30);
                        String inputt2 = new String(inputtext2);
                        phonenumber = inputt2;
                        Log.e("READ", inputt2);
                    } catch (Exception e) {
                        Log.e("READ!!", e.toString());
                    } finally {

                    }

                } else {
                    try {
                        serv2.createNewFile();

                        FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting.ini");
                        fop.write("".getBytes());
                        fop.close();
                    } catch (Exception e) {
                        Log.e("FILE", "/data/data/com.example.probcheck/tel_setting.ini");
                        Log.e("FILE", e.toString());
                    } finally {
                        Log.e("FILE", "ERR");
                    }
                }

                if (phonenumber.startsWith("+82")) {
                    phonenumber = phonenumber.replace("+82", "0");
                }
                phonenumber = phonenumber.substring(0, phonenumber.indexOf("\0"));

                File serv4 = new File("/data/data/com.example.probcheck/tel_setting3.ini");
                if (serv4.exists() == true) {
                    try {
                        byte[] inputtext4 = new byte[30];
                        FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/tel_setting3.ini");
                        fip.read(inputtext4, 0, 30);
                        String inputt4 = new String(inputtext4);
                        phonenumber2 = inputt4;
                        Log.e("READ", inputt4);
                    } catch (Exception e) {
                        Log.e("READ!!", e.toString());
                    } finally {

                    }

                } else {
                    try {
                        serv4.createNewFile();

                        FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting4.ini");
                        fop.write("".getBytes());
                        fop.close();
                    } catch (Exception e) {
                        Log.e("FILE", "/data/data/com.example.probcheck/tel_setting4.ini");
                        Log.e("FILE", e.toString());
                    } finally {
                        Log.e("FILE", "ERR");
                    }
                }

                if (phonenumber2.startsWith("+82")) {
                    phonenumber2 = phonenumber2.replace("+82", "0");
                }
                phonenumber2 = phonenumber2.substring(0, phonenumber2.indexOf("\0"));



                smsManager.sendTextMessage(phonenumber, null, "다음 위치에 고속도로 교통사고가 발생했습니다." + "\n위도:" + latitude + "\n" +
                            "경도:" + longitude, null, null);
                smsManager.sendTextMessage(phonenumber2, null, "다음 위치에 고속도로 교통사고가 발생했습니다." + "\n위도:" + latitude + "\n" +
                        "경도:" + longitude, null, null);



                /*Accident handling*/

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);


                break;
            case R.id.buttonno:
                gpsTracker = new GpsTracker(this);

                double latitude2 = gpsTracker.getLatitude();
                double longitude2 = gpsTracker.getLongitude();

                File serv3 = new File("/data/data/com.example.probcheck/tel_setting2.ini");
                if (serv3.exists() == true) {
                    try {
                        byte[] inputtext3 = new byte[30];
                        FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/tel_setting2.ini");
                        fip.read(inputtext3, 0, 30);
                        String inputt3 = new String(inputtext3);
                        phonenumber = inputt3;
                        Log.e("READ", inputt3);
                    } catch (Exception e) {
                        Log.e("READ!!", e.toString());
                    } finally {

                    }

                } else {
                    try {
                        serv3.createNewFile();

                        FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting2.ini");
                        fop.write("".getBytes());
                        fop.close();
                    } catch (Exception e) {
                        Log.e("FILE", "/data/data/com.example.probcheck/tel_setting2.ini");
                        Log.e("FILE", e.toString());
                    } finally {
                        Log.e("FILE", "ERR");
                    }
                }



                if (phonenumber.startsWith("+82")) {
                    phonenumber = phonenumber.replace("+82", "0");
                }
                phonenumber = phonenumber.substring(0, phonenumber.indexOf("\0"));

                Log.e("SMSSMS",phonenumber);

                smsManager.sendTextMessage(phonenumber, null, "보험사의 도움이 필요합니다. " + "\n위도:" + latitude2 + "\n" +
                            "경도:" + longitude2, null, null);



                this.finish();
                Intent intent2 = new Intent(this, noticemsg.class);
                startActivity(intent2);

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 9876: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }
}