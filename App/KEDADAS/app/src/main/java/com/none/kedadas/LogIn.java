package com.none.kedadas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view){

        Intent intent = new Intent(this, InitialPage.class);
        startActivity(intent);

        try {
            String _phone = getPhoneNumber();
        } catch (SecurityException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "No se pudo leer el número de teléfono", Toast.LENGTH_LONG);
            toast.show();
            System.out.println("Error!");
            // close current activity
            finish();
        }

        //TODO we have to check if we have the pair android id-phone number on ur database
        /*if (_phone.isEmpty()){

            System.out.println("Phone not available with the aplpication");

        }else{

            Intent intent = new Intent(this, InitialPage.class);
            startActivity(intent);

        }*/

    }

    //This method is used to take the phone number from the device automatically
    private String getPhoneNumber() throws SecurityException{
        TelephonyManager mTelephonyManager;
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getLine1Number();

    }
}
