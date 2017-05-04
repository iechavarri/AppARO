package com.none.kedadas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.FirebaseDatabase;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.data;
import static android.R.attr.progress;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private AdView mAdView;
    private boolean adShown = false;
    private InterstitialAd mInterstitialAd;
    private TextView mStatusTextView;

    private static final int SIGN_IN_CODE = 777;
    private GoogleApiClient googleApiClient;

    private SignInButton signInButton;

   // private FirebaseAuth firebaseAuth;
   // private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseCrash.report(new Exception("My first android fucking error"));


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

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.ad_view1);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

        //TODO Something about google login...
        //sign in object to request the email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //THIS CAN BE AN ERROR!!!
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //sign in client connecting to the api
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.LogInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });

    //    firebaseAuth = FirebaseAuth.getInstance();
    //    firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
    //        @Override
    //        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    //            FirebaseUser user = firebaseAuth.getCurrentUser();
    //            if (user != null) {
                    // TODO Hacer lo que se necesite hacer cuando logeamos.
    //                Toast.makeText(getApplicationContext(),"AUTHENTICATED",Toast.LENGTH_LONG).show();

    //            }
    //        }
    //    };

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

<<<<<<< HEAD
=======
//=======
      /*  TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
        infoTV.setText("Es necesario leer el estado del teléfono para la identificación");
//>>>>>>> 415c336a22627bcdd71b5eb47bd02adb56a1c0bc
>>>>>>> 20914d2c9051de218c64a7dfe7e016b0fc604b44
    }

/*    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }
*/
    @Override
<<<<<<< HEAD
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
=======
    /*public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case READ_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    read_phone_allowed = true;
                    TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
                    infoTV.setText("Permisos configurados correctamente");
>>>>>>> 20914d2c9051de218c64a7dfe7e016b0fc604b44

    }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Result returned from launching the Intent from

            if (requestCode == SIGN_IN_CODE) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleSignInResult(result);
            }
        }
<<<<<<< HEAD
         /*       if (result.isSuccess()) {
                    GoogleSignInAccount acct = result.getSignInAccount();
                    // Get account information
                    String mFullName = acct.getDisplayName();
                    String mEmail = acct.getEmail();
                    Log.d("FULL NAME", mFullName);
                    Log.d("EMAIL", mEmail);
=======
    }
    /*public void onClick(View view) {
//<<<<<<< HEAD

        //Intent intent = new Intent(this, MainPage.class);
        //startActivity(intent);
        // Create the InterstitialAd and set the adUnitId.

        /*if (!adShown){

            showInterstitial();
            adShown=true;

        }*/


        /*PhoneInfo phoneData = new PhoneInfo("", "");
        try {
            phoneData = getPhoneNumber();
        } catch (SecurityException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error de permisos", Toast.LENGTH_LONG);
            toast.show();
            Log.d(TAG, "Security exception", e);
            // close current activity
//=======
            if (read_phone_allowed) {
                phoneData = new PhoneInfo("", "");
                try {
                    phoneData = getPhoneNumber();
                    //TODO we have to check if we have the pair android id-phone number on ur database with phoneData (moving PhoneInfo class to its own class)
                    loginOrRegister(phoneData.deviceId, phoneData.simId);
                    TextView infoTV = (TextView) findViewById(R.id.phoneInfo);
                    infoTV.setText("deviceId: " + phoneData.deviceId + "\nsimId: " + phoneData.simId);
>>>>>>> 20914d2c9051de218c64a7dfe7e016b0fc604b44
                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "This is not working!",
                        Toast.LENGTH_LONG).show();
                    finish();


                }
            } */

        private void handleSignInResult(GoogleSignInResult result) {
            if (result.isSuccess()) {
                Toast.makeText(this, "This is working!",
                        Toast.LENGTH_LONG).show();
                //firebaseAuthWithGoogle(result.getSignInAccount());
            } else {
                Toast.makeText(this, "This is not working!",
                        Toast.LENGTH_LONG).show();
                //finish();

            }

        }
<<<<<<< HEAD
=======
    }*/

//=======
//>>>>>>> 415c336a22627bcdd71b5eb47bd02adb56a1c0bc
    //This method is used to take the phone number from the device automatically
    /*private PhoneInfo getPhoneNumber() throws SecurityException{
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
>>>>>>> 20914d2c9051de218c64a7dfe7e016b0fc604b44

/*        private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
            progressBar.setVisibility((View.VISIBLE));
            signInButton.setVisibility(View.GONE);

            AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    signInButton.setVisibility(View.VISIBLE);

                    if(!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"R.string.not_firebase_auth",Toast.LENGTH_LONG).show();
                    }
                }
            });

<<<<<<< HEAD
        } */

  /*  @Override
    protected  void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
=======
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        */
    /*}*/

    /*private class PhoneInfo {
        public String deviceId;
        public String simId;
        public PhoneInfo(String deviceId, String simId) {
            this.deviceId = deviceId;
            this.simId = simId;
>>>>>>> 20914d2c9051de218c64a7dfe7e016b0fc604b44
        }
    }*/







}

//    @Override
/**    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
<<<<<<< HEAD
    public void onClick(View view) {*/

=======
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
/*}

