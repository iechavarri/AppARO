package com.none.kedadas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.Intent;
import android.view.View;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view){

        Intent intent = new Intent(this, InitialPage.class);
        startActivity(intent);

        //String _phone = getPhoneNumber();
        //TODO we have to check if we have the pair android id-phone number on ur database
        /*if (_phone.isEmpty()){

            System.out.println("Phone not available with the aplpication");

        }else{

            Intent intent = new Intent(this, InitialPage.class);
            startActivity(intent);

        }*/

    }

    //This method is used to take the phone number from the device automatically
    private String getPhoneNumber(){

        TelephonyManager mTelephonyManager;
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getLine1Number();

    }
}
