package ru.hitpoint.lib.hitpoint.network;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {
    @POST
    Call<Request> doSmt();
}
