package com.ahchooseyou.ah_chooseyou;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class WelcomeActivity extends Activity implements Callback<Sneeze> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_welcome);

        if (!isLoggedIn()) {
            Toast.makeText(this, "Nobody logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            TextView tv = (TextView) findViewById(R.id.textView2);
            while(Profile.getCurrentProfile() == null); //spin
            tv.setText(Profile.getCurrentProfile().getName());

            ImageView profilepic = (ImageView) findViewById(R.id.imageView2);
            Picasso.with(getApplicationContext())
                    .load("https://graph.facebook.com/" + Profile.getCurrentProfile().getId() + "/picture?type=square&height=600&width=600")
                    .into(profilepic);
        }
    }

    public void sneeze(View view) {
        Toast.makeText(this, R.string.you_sneezed, Toast.LENGTH_SHORT).show();

        //TODO: get your match from backend

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ah-chooseyou.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SneezeService service = retrofit.create(SneezeService.class);

        String id = Profile.getCurrentProfile().getId();
        Date date = new Date();

        //Add Sneeze to database
        Sneeze mySneeze = new Sneeze(Profile.getCurrentProfile().getId(), date);
        Call<Sneeze> call = service.createSneeze(mySneeze);
        call.enqueue(this);



        //Get the closest Sneeze to our timestamp
        Call<Sneeze> closestSneezeCall = service.getMatchingSneeze(id, mySneeze.getTimestamp());
        closestSneezeCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Sneeze> call, Response<Sneeze> response) {
        Log.d("Response: ", response.body().toString());
        //Make sure this is the match, not the sneeze we just put in
        if (!response.body().user_id.equals(Profile.getCurrentProfile().getId())) {

            Intent intent = new Intent(this, FindMatchActivity.class);
            intent.putExtra("sneezeId", response.body().user_id);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Call<Sneeze> call, Throwable t) {
        Toast.makeText(this, R.string.sob, Toast.LENGTH_SHORT).show();
    }


    private boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }


    private interface SneezeService {

        @POST("/sneezes")
        Call<Sneeze> createSneeze(@Body Sneeze sneeze);

        @POST("/sneezes/{id}/match/{timestamp}")
        Call<Sneeze> getMatchingSneeze(@Path("id") String id, @Path("timestamp") String timestamp);
    }

    public static Bitmap getFacebookProfilePicture(String userId){
        URL imageURL = null;
        try {
            imageURL = new URL("https://graph.facebook.com/" + userId + "/picture?type=large");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

}
