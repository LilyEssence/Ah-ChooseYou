package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!LoginActivity.isLoggedIn) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public void sneeze(View view) {
        Toast.makeText(this, R.string.you_sneezed, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, FindMatchActivity.class));
    }
}
