package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        if (!isLoggedIn()) {
            Toast.makeText(this, "Nobody logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText(Profile.getCurrentProfile().getName());
        }

    }

    public void sneeze(View view) {
        Toast.makeText(this, R.string.you_sneezed, Toast.LENGTH_SHORT).show();
        //TODO: get your match from backend
        startActivity(new Intent(this, FindMatchActivity.class));
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this, LoginActivity.class));

    }
}
