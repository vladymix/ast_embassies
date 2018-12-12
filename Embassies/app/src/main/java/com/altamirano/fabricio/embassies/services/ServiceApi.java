package com.altamirano.fabricio.embassies.services;

import com.altamirano.fabricio.embassies.commons.EmbajadasAPI;
import com.altamirano.fabricio.embassies.commons.EmbajadasConsulados;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceApi {

    public static void getAllData(Callback<EmbajadasConsulados> callback){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://datos.madrid.es/egob/")
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.client(client)
                .build();

        EmbajadasAPI restClient = retrofit.create(EmbajadasAPI.class);
        Call<EmbajadasConsulados> call = restClient.loadData();
        call.enqueue(callback);
    }
}
