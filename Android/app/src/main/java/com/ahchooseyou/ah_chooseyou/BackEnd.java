package com.ahchooseyou.ah_chooseyou;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Lily on 10/22/2016.
 */

public class BackEnd {

    private static BackEnd ourInstance = new BackEnd();

    public static BackEnd getInstance() {
        return ourInstance;
    }

    private static final String baseUrl = "192.168.56.1";
    private SneezeService sneezeService;

    private BackEnd() {
        sneezeService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build()
                .create(SneezeService.class);
    }

    public List<Sneeze> getAllSneezes(){
        //TODO
        return null;
    }

    public Sneeze addSneeze(Sneeze sneeze){
        //TODO
        return null;
    }

    public Sneeze closestSneeze(Date date){
        //TODO
        return null;
    }

    private interface SneezeService {
        @GET("sneezes")
        Call<List<Sneeze>> listAllSneezes();

        @POST("sneezes")
        Call<Sneeze> createSneeze(@Body Sneeze sneeze);

        @GET("sneezes/{id}")
        Call<Sneeze> getSneeze(@Path("id") String id);
    }
}
