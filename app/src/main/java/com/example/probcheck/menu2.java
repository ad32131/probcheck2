package com.example.probcheck;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;

public class menu2 extends AppCompatActivity implements View.OnTouchListener {
    static float X1=0;
    static float X2=0;
    static int pageNumberToSee =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        ScrollView scrollb = findViewById(R.id.scrollb);
        TextView title = findViewById(R.id.title);
        TextView subtitle = findViewById(R.id.subtitle);
        ImageView centerimage = findViewById(R.id.centerimage);
        TextView contents = findViewById(R.id.contents);

        title.setText(R.string.title1);
        subtitle.setText(R.string.subtitle1);
        centerimage.setImageResource(R.drawable.centerimage1);
        contents.setText(R.string.contents1);


        scrollb.setOnTouchListener(this);



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TextView title = findViewById(R.id.title);
        TextView subtitle = findViewById(R.id.subtitle);
        ImageView centerimage = findViewById(R.id.centerimage);
        TextView contents = findViewById(R.id.contents);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                X1 = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                X2 = event.getRawX();
                //Toast.makeText(this, String.valueOf(X2-X1).toString(), Toast.LENGTH_SHORT).show();
                if((X2-X1) > 300){
                    Toast.makeText(this, "--", Toast.LENGTH_SHORT).show();
                    pageNumberToSee--;
                    if(pageNumberToSee < 1) pageNumberToSee=7;
                    switch(pageNumberToSee){
                        case 1:
                            title.setText(R.string.title1);
                            subtitle.setText(R.string.subtitle1);
                            centerimage.setImageResource(R.drawable.centerimage1);
                            contents.setText(R.string.contents1);
                            break;
                        case 2:
                            title.setText(R.string.title2);
                            subtitle.setText(R.string.subtitle2);
                            centerimage.setImageResource(R.drawable.centerimage2);
                            contents.setText(R.string.contents2);
                            break;
                        case 3:
                            title.setText(R.string.title3);
                            subtitle.setText(R.string.subtitle3);
                            centerimage.setImageResource(R.drawable.centerimage3);
                            contents.setText(R.string.contents3);
                            break;
                        case 4:
                            title.setText(R.string.title4);
                            subtitle.setText(R.string.subtitle4);
                            centerimage.setImageResource(R.drawable.centerimage4);
                            contents.setText(R.string.contents4);
                            break;
                        case 5:
                            title.setText(R.string.title5);
                            subtitle.setText(R.string.subtitle5);
                            centerimage.setImageResource(R.drawable.centerimage5);
                            contents.setText(R.string.contents5);
                            break;
                        case 6:
                            title.setText(R.string.title6);
                            subtitle.setText(R.string.subtitle6);
                            centerimage.setImageResource(R.drawable.centerimage6);
                            contents.setText(R.string.contents6);
                            break;
                        case 7:
                            title.setText(R.string.title7);
                            subtitle.setText(R.string.subtitle7);
                            centerimage.setImageResource(R.drawable.centerimage7);
                            contents.setText(R.string.contents7);
                            break;
                    }

                }else if ((X2-X1) < -300){
                    Toast.makeText(this, "++", Toast.LENGTH_SHORT).show();
                    pageNumberToSee++;
                    if(pageNumberToSee > 7) pageNumberToSee=1;
                    switch(pageNumberToSee){
                        case 1:
                            title.setText(R.string.title1);
                            subtitle.setText(R.string.subtitle1);
                            centerimage.setImageResource(R.drawable.centerimage1);
                            contents.setText(R.string.contents1);
                            break;
                        case 2:
                            title.setText(R.string.title2);
                            subtitle.setText(R.string.subtitle2);
                            centerimage.setImageResource(R.drawable.centerimage2);
                            contents.setText(R.string.contents2);
                            break;
                        case 3:
                            title.setText(R.string.title3);
                            subtitle.setText(R.string.subtitle3);
                            centerimage.setImageResource(R.drawable.centerimage3);
                            contents.setText(R.string.contents3);
                            break;
                        case 4:
                            title.setText(R.string.title4);
                            subtitle.setText(R.string.subtitle4);
                            centerimage.setImageResource(R.drawable.centerimage4);
                            contents.setText(R.string.contents4);
                            break;
                        case 5:
                            title.setText(R.string.title5);
                            subtitle.setText(R.string.subtitle5);
                            centerimage.setImageResource(R.drawable.centerimage5);
                            contents.setText(R.string.contents5);
                            break;
                        case 6:
                            title.setText(R.string.title6);
                            subtitle.setText(R.string.subtitle6);
                            centerimage.setImageResource(R.drawable.centerimage6);
                            contents.setText(R.string.contents6);
                            break;
                        case 7:
                            title.setText(R.string.title7);
                            subtitle.setText(R.string.subtitle7);
                            centerimage.setImageResource(R.drawable.centerimage7);
                            contents.setText(R.string.contents7);
                            break;
                    }
                }

                break;

        }


        return false;
    }
}
