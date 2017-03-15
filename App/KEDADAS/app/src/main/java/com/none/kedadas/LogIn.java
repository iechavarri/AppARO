package com.none.kedadas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.Intent;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    protected void onCick(){

        String _phone = getPhoneNumber();
        //TODO we have to check if we have the pair android id-phone number on ur database
        /*if (_phone.isEmpty()){

            System.out.println("Phone not available with the aplpication");

        }*/
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    //This method is used to take the phone number from the device automatically
    private String getPhoneNumber(){

        TelephonyManager mTelephonyManager;
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getLine1Number();

    }
}
