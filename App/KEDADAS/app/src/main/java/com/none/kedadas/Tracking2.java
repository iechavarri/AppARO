package com.none.kedadas;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;


public class Tracking2 extends FragmentActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;

    private LatLng position;

    private String kdd;

    private ArrayList<MarkerOptions> markers = new ArrayList<>();

    private ArrayList<Kedada.Users> usuarios = new ArrayList<>();

    protected static final String TAG = "MainActivity";

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_location_demo);

        kdd = getIntent().getExtras().get("kedada").toString();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("kdds/"+kdd+"/users");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Kedada.Users user = (Kedada.Users) dataSnapshot.getValue(Kedada.Users.class);
                usuarios.add(user);
                double theLat = Double.parseDouble(user.getLatitud());
                double theLon = Double.parseDouble(user.getLongitud());
                markers.add(new MarkerOptions().position(new LatLng(theLat,theLon)).title(user.getUserId()));
                refreshMap();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Kedada.Users user = (Kedada.Users) dataSnapshot.getValue(Kedada.Users.class);
                String toFind = user.getUserId();
                int i;
                for (i = 0; i<usuarios.size();i++){
                    if (usuarios.get(i).getUserId().equals(toFind)){
                        break;
                    }
                }
                if (usuarios.get(i).getUserId().equals(toFind)){
                    double theLat = Double.parseDouble(usuarios.get(i).getLatitud());
                    double theLon = Double.parseDouble(usuarios.get(i).getLongitud());
                    markers.get(i).position(new LatLng(theLat,theLon));
                            //.title(usuarios.get(i).getUserId());
                }
                refreshMap();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("BASURA AQUI","FALLO EN LECTURA");
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buildGoogleApiClient();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(42.123241, -1.938423);
        mMap.addMarker(new MarkerOptions().position(position).title("Marker in mi posicion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mMap.addMarker(new MarkerOptions().position(sydney).title("My position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);*/
        //LatLng pamplona = new LatLng(42.797263,-1.6343405);
        //mMap.addMarker(new MarkerOptions().position(pamplona).title("Marker in mi posicion"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(pamplona));
        mMap.moveCamera(CameraUpdateFactory.zoomBy(10));

        //mMap.addMarker(new MarkerOptions().position(pamplona).title("My position"));
        //mMap.clear();
        for (MarkerOptions marcador : markers){
            mMap.addMarker(marcador);
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(pamplona));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //TODO mandar ubicacion inicial
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Toast.makeText(this, "Looking for location", Toast.LENGTH_LONG).show();
        if (mLastLocation != null) {
            position = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            Toast.makeText(this, R.string.location_detected, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Latitude :"+ mLastLocation.getLatitude() + ", longitude: "+ mLastLocation.getLongitude(), Toast.LENGTH_LONG).show();
            updateLocation(mLastLocation);
            //mMap.addMarker(new MarkerOptions().position(position).title("My position"));
            //mMap.clear();
            for (MarkerOptions marcador : markers){
                mMap.addMarker(marcador);
            }
            mMap.moveCamera(CameraUpdateFactory.zoomBy(16));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            //TODO send the coordinates to the server

            //Crear un arraylist de localizacion y definir su tamaño cuando reciba la primera oleada de localizaciones
        } else {
            Toast.makeText(this, R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
    }

    public void updateLocation(Location location){

        mLastLocation = location;
        //Toast.makeText(this, "Location changed", Toast.LENGTH_LONG).show();
        if (mLastLocation != null){
            position = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            Toast.makeText(this, R.string.location_changed, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Latitude :"+ mLastLocation.getLatitude() + ", longitude: "+ mLastLocation.getLongitude(), Toast.LENGTH_LONG).show();
            //mMap.addMarker(new MarkerOptions().position(position).title("My position"));
            //mMap.clear();
            //for (MarkerOptions marcador : markers){
            //    mMap.addMarker(marcador);
            //}

            HashMap h = new HashMap<String,String>();
            h.put("latitud", Double.toString(position.latitude));
            h.put("longitud", Double.toString(position.longitude));
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("kdds/"+kdd+"/users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
            myRef.updateChildren(h);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            //TODO send the coordinates to the server
        }else {
            Toast.makeText(this, R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }

    }
    private void refreshMap() {
        mMap.clear();
        LatLng lastpos = position;
        for (MarkerOptions marcador : markers){
            mMap.addMarker(marcador);
            lastpos = marcador.getPosition();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lastpos));
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        updateLocation(location);

    }

}
