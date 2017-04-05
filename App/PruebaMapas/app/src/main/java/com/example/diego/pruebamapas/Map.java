package com.example.diego.pruebamapas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class Map extends AppCompatActivity {

    private MapView mapView = null;
    private MapboxMap mapboxMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapboxAccountManager.start(this, getString(R.string.access_token));
        setContentView(R.layout.activity_map);



        mapView = (MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap map) {
                /*MarkerViewOptions markerViewOptions = new MarkerViewOptions()
                        .position(new LatLng(42.8017, -1.6262)
                        .title("Hello World!")
                        .snippet("Welcome to my marker."));*/
                mapboxMap = map;
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(48.13863, 11.57603))
                        .title("Hello World!")
                        .snippet("Welcome to my marker."));
                mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.13863, 11.57603), 10));
                //mapboxMap.addMarker(markerViewOptions);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
