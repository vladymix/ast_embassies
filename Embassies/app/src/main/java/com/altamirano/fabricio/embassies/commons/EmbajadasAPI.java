package com.altamirano.fabricio.embassies.commons;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface EmbajadasAPI {

    @Headers("Content-Type: application/json")
    @GET("catalogo/201000-0-embajadas-consulados.json")
    Call<EmbajadasConsulados> loadData();
}
