package com.altamirano.fabricio.embassies.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.altamirano.fabricio.embassies.R;
import com.altamirano.fabricio.embassies.commons.EmbajadasAPI;
import com.altamirano.fabricio.embassies.commons.EmbajadasConsulados;
import com.altamirano.fabricio.embassies.commons.LastSearch;
import com.altamirano.fabricio.embassies.database.EmbassiesSqlite;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentSearch extends Fragment implements Callback<EmbajadasConsulados> {

    @BindView(R.id.ed_lat)
    EditText ed_lat;
    @BindView(R.id.ed_lon)
    EditText ed_lon;


    public FragmentSearch(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResponse(Call<EmbajadasConsulados> call, Response<EmbajadasConsulados> response) {
        if(response!=null){

        }
    }

    @Override
    public void onFailure(Call<EmbajadasConsulados> call, Throwable t) {

    }

    @OnClick(R.id.btn_search)
    public void onSearch() {
        try{
            LastSearch item = new LastSearch();
            item.setLon(Double.parseDouble(ed_lon.getText().toString()));
            item.setLat(Double.parseDouble(ed_lat.getText().toString()));
            item.setDate(Calendar.getInstance().getTime());
            EmbassiesSqlite dataBase = EmbassiesSqlite.getInstance(getContext());
            dataBase.insert(item);
            callerApi();
            Toast.makeText(this.getContext(),"Añadida correctamente",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this.getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void callerApi() {

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
        call.enqueue(this);
    }
}
