package ru.hitpoint.lib.hitpoint.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetServiceManager {
//    private static final String BASE_URL = "http://192.168.0.100:8080";
    private static final String BASE_URL = "http://superapi.pythonanywhere.com";
    private Retrofit retrofit;

    public ApiService getApiService(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
