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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://ec2-52-43-198-218.us-west-2.compute.amazonaws.com/";
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int READ_PHONE = 1;
    private boolean read_phone_allowed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_login);
        TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
        infoTV.setText("Es necesario leer el estado del teléfono para la identificación");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case READ_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    read_phone_allowed = true;
                    TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
                    infoTV.setText("Permisos configurados correctamente");

                } else {
                    TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
                    infoTV.setText("Imposible continuar sin permisos de identificación. Si el problema persiste, reinstala la aplicación");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public void onClick(View view) {
        if (read_phone_allowed) {
            PhoneInfo phoneData = new PhoneInfo("", "");
            try {
                phoneData = getPhoneNumber();
                //TODO we have to check if we have the pair android id-phone number on ur database with phoneData (moving PhoneInfo class to its own class)
                loginOrRegister(phoneData.deviceId, phoneData.simId);
                TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
                infoTV.setText("deviceId: " + phoneData.deviceId + "\nsimId: " + phoneData.simId);
                Intent intent = new Intent(this, MainPage.class);
                startActivity(intent);
                finish();
            } catch (SecurityException e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error de permisos", Toast.LENGTH_LONG);
                toast.show();
                Log.d(TAG, "Security exception", e);
                // close current activity
            }
        } else {
            requestReadPhonePermission();
        }
    }
    private void requestReadPhonePermission() {
        // TODO: Check different cases (ie first deny then allow)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Por favor, reinicia la aplicación", Toast.LENGTH_LONG);
                toast.show();
            }else{
            }
        }
    }
    //This method is used to take the phone number from the device automatically
    private PhoneInfo getPhoneNumber() throws SecurityException{
        TelephonyManager mTelephonyManager;
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Log.d(TAG, "TelephonyManager.getSimSerialNumber = " + mTelephonyManager.getSimSerialNumber());
        Log.d(TAG, "TelephonyManager.getDeviceId = " + mTelephonyManager.getDeviceId());
        Log.d(TAG, "TelephonyManager.getLine1Number = " + mTelephonyManager.getLine1Number());
        Log.d(TAG, "TelephonyManager.getSubscriberId = " + mTelephonyManager.getSubscriberId());
        return new PhoneInfo(mTelephonyManager.getDeviceId(), mTelephonyManager.getSimSerialNumber());
        /*
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_PHONE);
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
        */
    }

    private class PhoneInfo {
        public String deviceId;
        public String simId;
        public PhoneInfo(String deviceId, String simId) {
            this.deviceId = deviceId;
            this.simId = simId;
        }
    }

    private ApiInterface getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }

    private void loginOrRegister(String id, String phone){
        ApiInterface signIn = this.getInterfaceService();
        Call<Login> signInService = signIn.authenticate(id,phone);
        signInService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login mLoginObject = response.body();
                String returnedResponse = mLoginObject.isLogin;
                Toast.makeText(LoginActivity.this, "Returned " + returnedResponse, Toast.LENGTH_LONG).show();
                //showProgress(false);
                if (returnedResponse.trim().equals("1")) {
                    // Succesfull login
                    Toast toast = Toast.makeText(LoginActivity.this, "Login Succesfull", Toast.LENGTH_LONG);
                    toast.show();

                } else {
                    //Not registered. Automatically register
                    Toast toast2 = Toast.makeText(LoginActivity.this, "Not registered. So registering now", Toast.LENGTH_LONG);
                    toast2.show();


                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                call.cancel();
                Toast.makeText(LoginActivity.this,"Please check your network connection and internet permission",Toast.LENGTH_LONG).show();
            }
        });



/**
    private void loginProcessWithRetrofit(final String email, String password){
        ApiInterface mApiService = this.getInterfaceService();
        Call<Login> mService = mApiService.authenticate(email, password);
        mService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login mLoginObject = response.body();
                String returnedResponse = mLoginObject.isLogin;
                Toast.makeText(LoginActivity.this, "Returned " + returnedResponse, Toast.LENGTH_LONG).show();
                //showProgress(false);
                if(returnedResponse.trim().equals("1")){
                    // redirect to Main Activity page
                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    loginIntent.putExtra("EMAIL", email);
                    startActivity(loginIntent);
                }
                if(returnedResponse.trim().equals("0")){
                    // use the registration button to register
                    failedLoginMessage.setText(getResources().getString(R.string.registration_message));
                    mPasswordView.requestFocus();
                }
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                call.cancel();
                Toast.makeText(LoginActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void registrationProcessWithRetrofit(final String email, String password){
        ApiInterface mApiService = this.getInterfaceService();
        Call<Login> mService = mApiService.registration(email, password);
        mService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login mLoginObject = response.body();
                String returnedResponse = mLoginObject.isLogin;
                //showProgress(false);
                if(returnedResponse.trim().equals("1")){
                    // redirect to Main Activity page
                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    loginIntent.putExtra("EMAIL", email);
                    startActivity(loginIntent);
                }
                if(returnedResponse.trim().equals("0")){
                    // use the registration button to register
                    failedLoginMessage.setText(getResources().getString(R.string.registration_failed));
                    mPasswordView.requestFocus();
                }
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                call.cancel();
                Toast.makeText(LoginActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }*/
}
}
