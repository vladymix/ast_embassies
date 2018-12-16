package com.altamirano.fabricio.embassies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.altamirano.fabricio.embassies.R;
import com.altamirano.fabricio.embassies.adapters.ResultAdapter;
import com.altamirano.fabricio.embassies.commons.EmbajadasConsulados;
import com.altamirano.fabricio.embassies.commons.LastSearch;
import com.altamirano.fabricio.embassies.commons.Result;
import com.altamirano.fabricio.embassies.database.EmbassiesSqlite;
import com.altamirano.fabricio.embassies.services.ServiceApi;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAllData extends Fragment implements Callback<EmbajadasConsulados> {

    private static ArrayList<Result> results = new ArrayList<>();

    ResultAdapter adapter;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.listHistory)
    ListView listHistory;

    public FragmentAllData() {
        // need
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (results.size() == 0) {
            ServiceApi.getAllData(this);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        this.listHistory.setEmptyView(this.empty);
        this.adapter = new ResultAdapter(this.getContext(), results);
        this.listHistory.setAdapter(adapter);
        return view;
    }

    @Override
    public void onFailure(Call<EmbajadasConsulados> call, Throwable t) {
        try {
            Toast.makeText(this.getContext(), "Error de conexion", Toast.LENGTH_LONG).show();
        } catch (Exception ignore) {
        }
    }

    @OnItemClick(R.id.listHistory)
    public void onItemClick(int i) {
        Result item = this.adapter.getItem(i);
        EmbassiesSqlite dataBase = EmbassiesSqlite.getInstance(getContext());
        LastSearch lastSearch = new LastSearch();
        lastSearch.set_id(Integer.valueOf(item.getId()));
        lastSearch.setTitle(item.getTitle());
        lastSearch.setLocality(item.getAddress().getLocality());
        lastSearch.setStreetaddress(item.getAddress().getStreetaddress());

        if(item.getLocation()!=null){
            lastSearch.setLat(item.getLocation().getLatitude());
            lastSearch.setLon(item.getLocation().getLongitude());
        }

        lastSearch.setDate(Calendar.getInstance().getTime());

        dataBase.upsert(lastSearch);
        Intent intent = new Intent(this.getActivity(), MapsActivity.class);
        intent.putExtra("LastSearch", lastSearch);
        this.getActivity().startActivity(intent);
    }

    @Override
    public void onResponse(Call<EmbajadasConsulados> call, Response<EmbajadasConsulados> response) {
        if (response != null && response.body() != null && response.body().graph != null) {
            results.clear();
            results.addAll(response.body().graph);
            this.adapter.notifyDataSetChanged();
           /* for(int i=0; i< results.size();i++){
                // Save all
                this.onItemClick(i);
            }*/
        }
    }

    public void filterResults(String textToSearch){
        this.adapter.getFilter().filter(textToSearch);
        this.adapter.notifyDataSetChanged();
    }
}
