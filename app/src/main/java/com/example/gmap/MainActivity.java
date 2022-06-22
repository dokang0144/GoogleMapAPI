package com.example.gmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng SUNGIL = new LatLng(37.433774, 127.145662);
    private double x, y;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SUNGIL);
        markerOptions.title("성일정보고등학교");
        markerOptions.snippet("성남시의 고등학교");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SUNGIL, 10));

    }
    public LatLng getRandomLocation(LatLng c, int radius) {
        double d2r = Math.PI / 180;
        double r2d = 180 / Math.PI;
        double earth_rad = 6378000f; //지구 반지름 근사값

        double r = new Random().nextInt(radius) + new Random().nextDouble();
        double rlat = (r / earth_rad) * r2d;
        double rlng = rlat / Math.cos(c.latitude * d2r);

        double theta = Math.PI * (new Random().nextInt(2) + new Random().nextDouble());
        y = c.longitude + (rlng * Math.cos(theta));
        x = c.latitude + (rlat * Math.sin(theta));
        return new LatLng(x, y);
    }

    public void onClick(View view) {
        getRandomLocation(SUNGIL, 1000);

        count++;

        LatLng RANDOM = new LatLng(x, y);

        Toast.makeText(getApplicationContext(), "보물을 찾았습니다.", Toast.LENGTH_SHORT).show();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(RANDOM);
        markerOptions.title(count+"번째 보물");
        markerOptions.snippet(count+"번째 보물입니다.");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RANDOM, 17));
    }
}