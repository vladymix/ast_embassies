package com.altamirano.fabricio.embassies.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.altamirano.fabricio.embassies.R;
import com.altamirano.fabricio.embassies.commons.LastSearch;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.tv_streetAddress)
    TextView tv_streetAddress;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private LastSearch lastSearch = null;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        if (intent != null) {
            lastSearch = (LastSearch) intent.getSerializableExtra("LastSearch");
            tv_title.setText(lastSearch.getTitle());
            tv_streetAddress.setText(lastSearch.getStreetaddress());
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (lastSearch != null && lastSearch.getLat()!=0 && lastSearch.getLon()!=0) {
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(lastSearch.getLat(), lastSearch.getLon());
            mMap.addMarker(new MarkerOptions().position(sydney).title(lastSearch.getTitle()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        }
    }
}
