package com.example.practicafinal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WS {
    @GET
    Call<String> ObtenerRuta(@Url String url);
}
