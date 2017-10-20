package com.yobel.mapexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    boolean mapReady = false;
    LatLng jombang = new LatLng(-7.5622506, 112.2337198);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        mMap = googleMap;
        mMap.addPolyline(new PolylineOptions().geodesic(true).add(new LatLng(-7.551187, 112.224978))
                .add(new LatLng(-7.556107, 112.232671))
                .add(new LatLng(-7.575390, 112.236555))
                .add(new LatLng(-7.551187, 112.224978)));
        changePosition(jombang);
    }

    void changePosition(LatLng target) {
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(target)
                .zoom(12)
                .bearing(90)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 3000, null);
    }
}
