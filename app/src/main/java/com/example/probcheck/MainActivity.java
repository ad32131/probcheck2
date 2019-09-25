package com.example.probcheck;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.renderscript.Sampler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.cert.Extension;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FirebaseDatabase database;
    private DatabaseReference myRef18;
    private DatabaseReference myRef19;

    static double longitude;   //경도
    static double latitude;   //위도
    static double altitude;   //고도
    static float accuracy;    //정확도
    static String provider;   //위치제공자

    private static final String TAG = "MainActivity";

    private GpsTracker gpsTracker;
    private static final int PERMISSIONS_REQUEST_READ_SMS = 100;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    TextView jjj;
    Button aaa;


    DatabaseReference mDatabase;
    robotsend userdata;

    @Override
    public void onClick(View view) {

        Vibrator vibe=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(40);

        DatabaseReference nRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference nfoodinfoRef = nRootRef.child("command").child("opener").child("userName")//;

        switch (view.getId()) {
            case R.id.roboton:
                mDatabase = FirebaseDatabase.getInstance().getReference();
                userdata = new robotsend("GO");
                mDatabase.child("command").child("mobile").setValue(userdata);
                nfoodinfoRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
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

                Toast.makeText (this,"로봇이 작동됩니다.", Toast.LENGTH_SHORT).show();


                break;
            case R.id.robotoff:
                mDatabase = FirebaseDatabase.getInstance().getReference();
                userdata = new robotsend("BACK");
                mDatabase.child("command").child("robot").setValue(userdata);
                Toast.makeText (this,"로봇이 꺼집니다.", Toast.LENGTH_SHORT).show();


                DatabaseReference nnRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference nnfoodinfoRef = nnRootRef.child("python").child("command").child("cradle");
                nnfoodinfoRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String text = dataSnapshot.getValue(String.class);
                        if(text.equals("0")){
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("Sensor").child("shock").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("Sensor").child("sound").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("command").child("BREAK").setValue(userdata);

                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("command").child("cradle").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("command").child("distance").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("command").child("ground").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("command").child("mobile").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("command").child("opener").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            userdata = new robotsend(" ");
                            mDatabase.child("command").child("robot").setValue(userdata);
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                break;
            case R.id.menu1:
                Intent intent=new Intent(this, menu1.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                Intent intent2=new Intent(this, menu2.class);
                startActivity(intent2);
                break;
            case R.id.menu3:
                Intent intent3=new Intent(this, menu3.class);
                startActivity(intent3);
                break;
            case R.id.menu4:
                Intent intent4=new Intent(this, menu4.class);
                startActivity(intent4);
                break;
            case R.id.Activate:
                String inputt = null;
                ImageView Activate = findViewById(R.id.Activate);
                try {
                    byte[] inputtext = new byte[1];
                    FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/service_status.ini");
                    fip.read(inputtext, 0, 1);
                    inputt = new String(inputtext);
                    Log.e("READ", inputt);
                } catch (Exception e) {
                    Log.e("READ", e.toString());
                } finally {
                }
                if (inputt.compareTo("0") == 0) {
                    try {
                        FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/service_status.ini");
                        fop.write("1".getBytes());
                        fop.close();
                    } catch (Exception e) {
                        Log.e("FILE", "/data/data/com.example.probcheck/service_status.ini");
                        Log.e("FILE", e.toString());
                    } finally {
                        Log.e("FILE", "ERR");
                    }
                    Activate.setImageResource(android.R.drawable.presence_online);//ic_notification_overlay
                } else {
                    try {
                        FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/service_status.ini");
                        fop.write("0".getBytes());
                        fop.close();
                    } catch (Exception e) {
                        Log.e("FILE", "/data/data/com.example.probcheck/service_status.ini");
                        Log.e("FILE", e.toString());
                    } finally {
                        Log.e("FILE", "ERR3");
                    }
                    Activate.setImageResource(android.R.drawable.presence_offline);
                }


                break;
            case R.id.Setting:

                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView Activate = findViewById(R.id.Activate);
        ImageView Setting = findViewById(R.id.Setting);

        gpsTracker = new GpsTracker(MainActivity.this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {
                Log.e("Perm","Send_SMS");


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        9876);

            }
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.e("Perm","Send_SMS");


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        9876);

            }
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
                Log.e("Perm","Send_SMS");


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        9876);

            }


            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PRIVILEGED)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PRIVILEGED)) {
                    Log.e("Perm","Send_SMS");


                } else {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PRIVILEGED},
                            9876);
                }
            }
            else{if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PRIVILEGED)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PRIVILEGED)) {
                    Log.e("Perm","Send_SMS");


                } else {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CALL_PRIVILEGED},
                            9876);
                }
            }
            else{
            }
            }

        }

        startService(new Intent(this, service.class));

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();
        Log.e("pos", "latitude:" + latitude + " longitude:" + longitude);

        File serv = new File("/data/data/com.example.probcheck/service_status.ini");
        if(serv.exists()==true) {
            try {
                byte [] inputtext = new byte[1];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/service_status.ini");
                fip.read(inputtext,0,1);
                String inputt = new String(inputtext);
                if(inputt.compareTo("0")==0){
                    Activate.setImageResource(android.R.drawable.presence_offline);//ic_notification_overlay
                }else{
                    Log.e("a","a");
                    Activate.setImageResource(android.R.drawable.presence_online);//ic_notification_overlay
                }
                Log.e("READ", inputt);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }

        } else {
            try {
                serv.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/service_status.ini");
                fop.write("0".getBytes());
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/service_status.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }
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

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
