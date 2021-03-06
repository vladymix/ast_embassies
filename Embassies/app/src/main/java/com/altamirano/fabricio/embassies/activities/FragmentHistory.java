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

import com.altamirano.fabricio.embassies.R;
import com.altamirano.fabricio.embassies.adapters.LastSearchAdapter;
import com.altamirano.fabricio.embassies.commons.LastSearch;
import com.altamirano.fabricio.embassies.commons.Result;
import com.altamirano.fabricio.embassies.database.EmbassiesSqlite;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class FragmentHistory extends Fragment {
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.listHistory)
    ListView listHistory;

    LastSearchAdapter adapter;

    public FragmentHistory() {
        // is need
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EmbassiesSqlite dataBase = EmbassiesSqlite.getInstance(getContext());
        ArrayList<LastSearch> searches = dataBase.getLastSearchs();
        this.adapter = new LastSearchAdapter(this.getContext(), searches);
        this.listHistory.setAdapter(this.adapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        this.listHistory.setEmptyView(this.empty);
        return view;
    }
    @OnItemClick(R.id.listHistory)
    public void onItemClick(int i) {
        LastSearch lastSearch = this.adapter.getItem(i);
        Intent intent = new Intent(this.getActivity(), MapsActivity.class);
        intent.putExtra("LastSearch", lastSearch);
        this.getActivity().startActivity(intent);
    }
}
