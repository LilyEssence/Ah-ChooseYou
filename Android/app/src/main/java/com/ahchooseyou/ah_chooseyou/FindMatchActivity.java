package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONException;

public class FindMatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match);

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + Profile.getCurrentProfile().getId(),
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        String name = null;
                        try {
                            name = response.getJSONObject().getString("name");
                            tv.setText("A Match has been found: " + name + "!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            tv.setText("Your match is invalid :(");
                        }
                    }
                }
        ).executeAsync();
    }

    public void messageYourMatch(View view) {
        Toast.makeText(this, R.string.not_yet_implemented, Toast.LENGTH_SHORT).show();
    }
}
