package com.none.kedadas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by iecha on 10/04/2017.
 */

public interface ApiInterface {
    @GET("api/index.php/{id}/{phone}")
    Call<Login> authenticate(@Path("id") String id, @Path("phone") String phone);
    @POST("api/index.php/{id}/{phone}")
    Call<Login> registration(@Path("id") String email, @Path("phone") String phone);
}
