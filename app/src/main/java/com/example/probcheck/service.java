package com.example.probcheck;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class service extends Service {
    //this function is read data in firebase database for display push notification
    DatabaseReference nRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference nfoodinfoRef = nRootRef.child("command").child("accident");


    private GpsTracker gpsTracker;
    private static final int PERMISSIONS_REQUEST_READ_SMS = 100;


    Intent intent;
    PendingIntent pendingIntent;
    int setting_count=6;
    String phonenumber="";
    String phonenumber2="";

    DatabaseReference mDatabase;
    robotsend userdata;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void alarm() {

        File serv2 = new File("/data/data/com.example.probcheck/setcount.ini");
        if(serv2.exists()==true) {
            try {
                byte [] inputtext2 = new byte[1];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/setcount.ini");
                fip.read(inputtext2,0,1);
                String inputt2 = new String(inputtext2);
                setting_count = Integer.parseInt(inputt2);
                Log.e("READ", inputt2);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }

        } else {
            try {
                serv2.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/setcount.ini");
                fop.write("6".getBytes());
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/setcount.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }







        File serv3 = new File("/data/data/com.example.probcheck/tel_setting.ini");
        if(serv3.exists()==true) {
            try {
                byte [] inputtext3 = new byte[30];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/tel_setting.ini");
                fip.read(inputtext3,0,30);
                String inputt3 = new String(inputtext3);
                phonenumber = inputt3;
                Log.e("READ", inputt3);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }

        } else {
            try {
                serv3.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting.ini");
                fop.write("".getBytes());
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/tel_setting.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }

        File serv4 = new File("/data/data/com.example.probcheck/tel_setting3.ini");
        if(serv4.exists()==true) {
            try {
                byte [] inputtext4 = new byte[30];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/tel_setting3.ini");
                fip.read(inputtext4,0,30);
                String inputt4 = new String(inputtext4);
                phonenumber2 = inputt4;
                Log.e("READ", inputt4);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }

        } else {
            try {
                serv4.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting3.ini");
                fop.write("".getBytes());
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/tel_setting3.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }

        if(phonenumber.startsWith("+82")){
            phonenumber = phonenumber.replace("+82", "0");
        }
        phonenumber = phonenumber.substring(0,phonenumber.indexOf("\0"));

        if (phonenumber2.startsWith("+82")) {
            phonenumber2 = phonenumber2.replace("+82", "0");
        }
        phonenumber2 = phonenumber2.substring(0, phonenumber2.indexOf("\0"));

        File serv = new File("/data/data/com.example.probcheck/notice.ini");
        if(serv.exists()==true) {
            try {
                byte [] inputtext = new byte[1];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/notice.ini");
                fip.read(inputtext,0,1);
                String inputt = new String(inputtext);
                inputt = String.valueOf(Integer.parseInt(inputt)+1);


                String channelId = "12345";
                String channelName = "push";

                NotificationManager notifManager

                        = (NotificationManager) getSystemService  (Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel mChannel = new NotificationChannel(
                            channelId, channelName, importance);

                    notifManager.createNotificationChannel(mChannel);



                }

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext(), channelId);

                intent = new Intent (this, notice.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                builder.setContentTitle(inputt+"번째 알림입니다.") // required
                        .setContentText("사고 발생")  // required
                        .setDefaults(Notification.DEFAULT_ALL) // 알림, 사운드 진동 설정
                        .setAutoCancel(true) // 알림 터치시 반응 후 삭제
                        .setSound(defaultSoundUri)
                        .setSmallIcon(android.R.drawable.btn_star)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fire))
                        .setBadgeIconType(R.drawable.fire)
                        .setVibrate(new long[]{500,500})

                        .setContentIntent(pendingIntent);

                notifManager.notify(0, builder.build());

                if(Integer.parseInt(inputt) > setting_count-1){
                    Log.e("warning!!","warning!!");
                    /*Accident handling*/
                    gpsTracker = new GpsTracker(this);

                    double latitude = gpsTracker.getLatitude();
                    double longitude = gpsTracker.getLongitude();

                    DatabaseReference nRootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference nfoodinfoRef = nRootRef.child("command").child("opener").child("userName");

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

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phonenumber, null, "다음 위치에 고속도로 교통사고가 발생했습니다.1"+"\n위도:"+latitude+"\n" +
                            "경도:"+longitude, null, null);
                    /*Accident handling*/
                    smsManager.sendTextMessage(phonenumber2, null, "다음 위치에 고속도로 교통사고가 발생했습니다.2" + "\n위도:" + latitude + "\n" +
                            "경도:" + longitude, null, null);


                    try{
                        Log.e("error working!!","error working!!");
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
                else{
                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/notice.ini");
                fop.write(inputt.getBytes());
                fop.close();

                }

                if(inputt.compareTo("0")==0){

                }else{
                    Log.e("a","a");

                }
                Log.e("READ", inputt);
            }catch (Exception e){
                Log.e("READ",e.toString());
            }finally {

            }

        } else {
            try {
                serv.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/notice.ini");
                fop.write("1".getBytes());
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/notice.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }

        Intent intent=new Intent(this, notice.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        Log.v("service", "service");



        nfoodinfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                if(text.equals("Warning")){
                    alarm();
                    Log.v("alarm","alarm");
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
