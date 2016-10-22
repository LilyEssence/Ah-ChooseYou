package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {

    public static boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isLoggedIn = true;

    }

    public void login(View view) {
        Intent loginIntent = new Intent(this, MainActivity.class);
        loginIntent.putExtra("isLoggedIn", true);
        startActivity(loginIntent);
    }
}
