package com.none.kedadas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LogIn extends AppCompatActivity {
    private static final String TAG = LogIn.class.getSimpleName();
    private static final int READ_PHONE = 1;
    private int tryes = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_login);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case READ_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public void onClick(View view) {

        //Intent intent = new Intent(this, MainPage.class);
        //startActivity(intent);
        PhoneInfo phoneData = new PhoneInfo("","");
        try {
            phoneData = getPhoneNumber();
        } catch (SecurityException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error de permisos", Toast.LENGTH_LONG);
            toast.show();
            Log.d(TAG, "Security exception",e);
            // close current activity
        }
        TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
        infoTV.setText("deviceId: " + phoneData.deviceId + "\nsimId: " + phoneData.simId);
        //TODO we have to check if we have the pair android id-phone number on ur database with phoneData (moving PhoneInfo class to its own class)
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    //This method is used to take the phone number from the device automatically
    private PhoneInfo getPhoneNumber() throws SecurityException{
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        READ_PHONE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        TelephonyManager mTelephonyManager;
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Log.d(TAG, "TelephonyManager.getSimSerialNumber = " + mTelephonyManager.getSimSerialNumber());
        Log.d(TAG, "TelephonyManager.getDeviceId = " + mTelephonyManager.getDeviceId());
        Log.d(TAG, "TelephonyManager.getLine1Number = " + mTelephonyManager.getLine1Number());
        Log.d(TAG, "TelephonyManager.getSubscriberId = " + mTelephonyManager.getSubscriberId());
        return new PhoneInfo(mTelephonyManager.getDeviceId(), mTelephonyManager.getSimSerialNumber());
    }

    private class PhoneInfo {
        public String deviceId;
        public String simId;
        public PhoneInfo(String deviceId, String simId) {
            this.deviceId = deviceId;
            this.simId = simId;
        }
    }
}
