package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    String sneezeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match);

        Intent intent = getIntent();
        sneezeId = intent.getStringExtra("sneezeId");
        Log.d("Sneeze Id", sneezeId);

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + sneezeId,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        /**
                         * hide progresss
                         */
                        ProgressBar progressbar = (ProgressBar) findViewById(R.id.progressBar2);
                        progressbar.setVisibility(View.INVISIBLE);
                        try {
                            // star
                            ImageView star = (ImageView) findViewById(R.id.imageView);
                            star.setVisibility(View.VISIBLE);
                            // star animation
                            String name = response.getJSONObject().getString("name");
                            tv.setText("A Match has been found: " + name + "!");
                        } catch (JSONException e) {
                            // show a x
                            e.printStackTrace();
                            tv.setText("Your match is invalid :(");
                        }
                    }
                }
        ).executeAsync();
    }

    public void messageYourMatch(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "https://www.facebook.com/app_scoped_user_id/" + sneezeId));
        startActivity(browserIntent);
    }

    @Override
    public void onResponse(Call<Sneeze> call, Response<Sneeze> response) {

    }

    @Override
    public void onFailure(Call<Sneeze> call, Throwable t) {

    }
}
