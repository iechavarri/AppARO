package com.none.kedadas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by diego on 15/03/2017.
 */

public class MainPage extends AppCompatActivity{


    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-5411028378208022~7913959997");

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);



    }

    public void toNuevaKedada(View view){

        //Intent intent = new Intent(this, FirebaseTest.class);
        //startActivity(intent);
        addSomeTestKDDS();
    }

    public void masKedadas(View view){

        /*Intent intent = new Intent(this, OtrasKedadas.class);
        startActivity(intent);*/
        Intent intent = new Intent (this, MasKedadas.class);
        startActivity(intent);

    }

    public void toConfiguracion(View view){

        Intent intent = new Intent(this, Tracking.class);
        startActivity(intent);
    }

    public void toAyuda(View view){

        Intent intent = new Intent(this, Ayuda.class);
        startActivity(intent);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
    private void addSomeTestKDDS() {
        DateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("kdds");
        Kedada kdd1 = null, kdd2 = null;
        try {
            kdd1 = new Kedada("Primera kdd", dateFormatter.parse("05/05/2017"));
            kdd2 = new Kedada("Segunda kdd", dateFormatter.parse("06/05/2017"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DatabaseReference anotherRef;
        anotherRef = myRef.push();
        anotherRef.setValue(kdd1);
        anotherRef = myRef.push();
        anotherRef.setValue(kdd2);
    }
}
