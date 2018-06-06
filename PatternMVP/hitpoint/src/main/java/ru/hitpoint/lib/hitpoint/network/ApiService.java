package ru.hitpoint.lib.hitpoint.network;

import model.entities.HitPointAPI;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.hitpoint.lib.hitpoint.HitPoint;

public interface ApiService {
    @POST
    Call<Request> doSmt();

    @POST("/api/report/")
    Call<Request> report(@Body HitPointAPI hitPoint);
}
