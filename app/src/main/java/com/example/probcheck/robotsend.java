package com.example.probcheck;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public class robotsend {
    private String call;
    public robotsend(String call) {
        this.call = call;
    }
    public String getUserName() {
        return call;
    }
    public void setUserName(String call) {
        this.call = call;
    }
}
