package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindMatchActivity extends Activity implements Callback<Sneeze> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match);

        Intent intent = getIntent();
        String sneezeId = intent.getStringExtra("sneezeId");
        Log.d("Sneeze Id", sneezeId);

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + sneezeId,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        try {
                            String name = response.getJSONObject().getString("name");
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

    @Override
    public void onResponse(Call<Sneeze> call, Response<Sneeze> response) {

    }

    @Override
    public void onFailure(Call<Sneeze> call, Throwable t) {

    }
}
