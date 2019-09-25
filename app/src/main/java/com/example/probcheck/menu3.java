package com.example.probcheck;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class menu3 extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId() ){
            case R.id.textView8:
                TextView textView = (TextView)findViewById(R.id.textView8);
                Linkify.addLinks(textView, Linkify.PHONE_NUMBERS);
                String phonenumber = "";

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


                Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:"+phonenumber));
                startActivity(intent);

                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);

        TextView textView = (TextView)findViewById(R.id.textView8);
        Linkify.addLinks(textView, Linkify.PHONE_NUMBERS);

        String phonenumber = "";

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

        textView.setText("보험사전화번호\n"+phonenumber);





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
