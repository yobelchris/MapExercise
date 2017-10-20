package com.yobel.mapexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button mapBtn, satelliteBtn, hybridBtn;
    GoogleMap mMap;
    boolean mapReady = false;
    LatLng jombang = new LatLng(-7.5622506, 112.2337198);
    LatLng jogja = new LatLng(-7.8015878, 110.3695698);
    LatLng jakarta = new LatLng(-6.2414592, 106.8316321);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapBtn = (Button) findViewById(R.id.btnMap);
        satelliteBtn = (Button) findViewById(R.id.btnSatellite);
        hybridBtn = (Button) findViewById(R.id.btnHybrid);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    changePosition(jombang);
                }
            }
        });
        satelliteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    changePosition(jogja);
                }
            }
        });
        hybridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) {
                    changePosition(jakarta);
                }
            }
        });
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        mMap = googleMap;
        changePosition(jombang);
    }

    void changePosition(LatLng target) {
        mapBtn.setEnabled(false);
        satelliteBtn.setEnabled(false);
        hybridBtn.setEnabled(false);
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(target)
                .zoom(13)
                .bearing(90)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 3000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                mapBtn.setEnabled(true);
                satelliteBtn.setEnabled(true);
                hybridBtn.setEnabled(true);
            }

            @Override
            public void onCancel() {
                mapBtn.setEnabled(true);
                satelliteBtn.setEnabled(true);
                hybridBtn.setEnabled(true);
            }
        });
    }
}
