package com.none.kedadas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by diego on 22/03/2017.
 */

public class Ayuda extends AppCompatActivity {


    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

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

    public void SendEMail (View view){

        //Se va a poder mandar un email a la cuenta de correo que creamos aprincipio de curso
        String to = "apparog1@gmail.com";
        String subject = "Atencion al cliente KDD";
        Send(to, subject);

    }

    public void Send(String to, String subject){

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL,to);
        email.putExtra(Intent.EXTRA_SUBJECT,subject);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Email "));

    }

    public void VisitWeb (View view){

        Uri uri = Uri.parse("http://52.43.198.218/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
