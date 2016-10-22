package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FindMatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match);
    }

    public void messageYourMatch(View view) {
        Toast.makeText(this, R.string.not_yet_implemented, Toast.LENGTH_SHORT).show();
    }
}
