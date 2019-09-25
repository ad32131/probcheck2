package com.example.probcheck;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class menu4 extends AppCompatActivity implements Button.OnClickListener  {
    DatabaseReference mDatabase;
    robotsend userdata;
    EditText etNewMessage;

    private static final String TAG = "menu4";
    FirebaseDatabase database;
    DatabaseReference myRef;


    ImageView btUpdate;









    @Override
    public void onClick(View view) {
        Vibrator vibe=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(40);
        switch (view.getId()) {


            case R.id.settingte3:
                Toast.makeText(this, "사고처리번호2 저장완료.", Toast.LENGTH_SHORT).show();
                EditText tel_setting3 = findViewById(R.id.settel3);
                try {
                    FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting3.ini");
                    fop.write(tel_setting3.getText().toString().getBytes());
                    fop.close();
                }catch (Exception e) {
                    Log.e("FILE", "/data/data/com.example.probcheck/tel_setting3.ini");
                    Log.e("FILE",e.toString());
                }finally {
                    Log.e("FILE","ERR");
                }
                break;
            case R.id.settingtel2:
                Toast.makeText(this, "보험사전화번호 저장완료.", Toast.LENGTH_SHORT).show();
                EditText tel_setting2 = findViewById(R.id.settel2);
                try {
                    FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting2.ini");
                    fop.write(tel_setting2.getText().toString().getBytes());
                    fop.close();
                }catch (Exception e) {
                    Log.e("FILE", "/data/data/com.example.probcheck/tel_setting2.ini");
                    Log.e("FILE",e.toString());
                }finally {
                    Log.e("FILE","ERR");
                }
                break;
            case R.id.settingtel:
                Toast.makeText(this, "사고전화번호1 저장완료.", Toast.LENGTH_SHORT).show();
                EditText tel_setting = findViewById(R.id.settel);
                try {
                    FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting.ini");
                    fop.write(tel_setting.getText().toString().getBytes());
                    fop.close();
                }catch (Exception e) {
                    Log.e("FILE", "/data/data/com.example.probcheck/tel_setting.ini");
                    Log.e("FILE",e.toString());
                }finally {
                    Log.e("FILE","ERR");
                }
                break;
            case R.id.settingcount:
                Toast.makeText(this, "의식불명카운트 저장완료.", Toast.LENGTH_SHORT).show();
                EditText setcount = findViewById(R.id.setcount);
                try {
                    FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/setcount.ini");
                    fop.write(setcount.getText().toString().getBytes());
                    fop.close();
                }catch (Exception e) {
                    Log.e("FILE", "/data/data/com.example.probcheck/setcount.ini");
                    Log.e("FILE",e.toString());
                }finally {
                    Log.e("FILE","ERR");
                }
                break;
            case R.id.bt_update:
                Toast.makeText(this, "거리 저장완료.", Toast.LENGTH_SHORT).show();
                etNewMessage = (EditText) findViewById(R.id.et_newData);
                btUpdate = (ImageView) findViewById(R.id.bt_update);
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("command").child("distance");
                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newMessage = etNewMessage.getText().toString();
                        myRef.setValue(newMessage);

                    }

                });

                break;







        }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu4);
        EditText tel_setting = findViewById(R.id.settel);
        EditText tel_setting2 = findViewById(R.id.settel2);
        EditText tel_setting3 = findViewById(R.id.settel3);
        EditText setcount = findViewById(R.id.setcount);

        etNewMessage = (EditText) findViewById(R.id.et_newData);


        File serv = new File("/data/data/com.example.probcheck/tel_setting.ini");
        if(serv.exists()==true) {
            try {
                byte [] inputtext = new byte[30];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/tel_setting.ini");
                fip.read(inputtext,0,30);
                String inputt = new String(inputtext);
                tel_setting.setText(inputt);
                Log.e("READ", inputt);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }

        } else {
            try {
                serv.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting.ini");
                fop.write("0".getBytes());
                tel_setting.setText("0");
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/tel_setting.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }

        File serv2 = new File("/data/data/com.example.probcheck/setcount.ini");
        if(serv2.exists()==true) {
            try {
                byte [] inputtext2 = new byte[1];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/setcount.ini");
                fip.read(inputtext2,0,1);
                String inputt2 = new String(inputtext2);
                tel_setting.setText(inputt2);
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
                setcount.setText("6");
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/setcount.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }
        File serv3 = new File("/data/data/com.example.probcheck/tel_setting2.ini");
        if(serv3.exists()==true) {
            try {
                byte [] inputtext3 = new byte[30];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/tel_setting2.ini");
                fip.read(inputtext3,0,30);
                String inputt = new String(inputtext3);
                tel_setting.setText(inputt);
                Log.e("READ", inputt);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }
        } else {
            try {
                serv3.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting2.ini");
                fop.write("0".getBytes());
                tel_setting.setText("0");
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/tel_setting2.ini");
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
                tel_setting.setText(inputt4);
                Log.e("READ", inputt4);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }

        } else {
            try {
                serv4.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/tel_setting3.ini");
                fop.write("0".getBytes());
                tel_setting.setText("0");
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/tel_setting3.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }

        File serv5 = new File("/data/data/com.example.probcheck/bt_setting.ini");
        if(serv.exists()==true) {
            try {
                byte [] inputtext5 = new byte[30];
                FileInputStream fip = new FileInputStream("/data/data/com.example.probcheck/bt_setting.ini");
                fip.read(inputtext5,0,30);
                String inputt = new String(inputtext5);
                tel_setting.setText(inputt);
                Log.e("READ", inputt);
            }catch (Exception e){
                Log.e("READ!!",e.toString());
            }finally {

            }

        } else {
            try {
                serv.createNewFile();

                FileOutputStream fop = new FileOutputStream("/data/data/com.example.probcheck/etNewMessage.ini");
                fop.write("0".getBytes());
                etNewMessage.setText("0");
                fop.close();
            }catch (Exception e) {
                Log.e("FILE", "/data/data/com.example.probcheck/etNewMessage.ini");
                Log.e("FILE",e.toString());
            }finally {
                Log.e("FILE","ERR");
            }
        }
    }

}
