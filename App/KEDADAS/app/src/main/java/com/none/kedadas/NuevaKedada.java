package com.none.kedadas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by diego on 22/03/2017.
 */

public class NuevaKedada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_kedada);

    }

    public void crearChat(View view) throws ParseException {

        DateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("kdds");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Kedada.Users userToAdd = new Kedada.Users();

        String email = new String();
        String userid = new String();

        //Read KDD name
        EditText kddname_edit = (EditText) findViewById(R.id.add_kddname_edittext);
        String kddname = kddname_edit.getText().toString();
        if(TextUtils.isEmpty(kddname)) {
            kddname_edit.setError("Debes introducir un nombre de KDD");
            return;
        }

        //Read KDD date
        //NO FUNCIONA??
        EditText kdddate_edit = (EditText) findViewById(R.id.add_kdddate_edittext);
        String kdddate = kdddate_edit.getText().toString();
        if(TextUtils.isEmpty(kdddate)) {
            kdddate_edit.setError("Debes introducir una fecha de KDD");
            return;
        }

        //This is for take the actual user data
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userToAdd= new Kedada.Users(user.getUid(),user.getEmail(),"42.3","42.3");

            //userGroup = new Kedada.Users(user.getUid(),user.getEmail(),"42.3","42.3");

        }
        Kedada kdd1 = new Kedada(kddname,dateFormatter.parse(kdddate),userToAdd);
        kdd1.AddUser(new Kedada.Users("This is a fucking id","AndThisAnd@email.com","12.2","12.2"));
        DatabaseReference anotherRef;
        anotherRef = myRef.push();
        kdd1.setId(anotherRef.getKey());
        anotherRef.setValue(kdd1);
        anotherRef.child("users").push().getKey();

        //TODO Creating deeplink for the new kedada

        //In this section the new link will be created
        String url = new String("https://yhj7b.app.goo.gl/"+"?link="+anotherRef.getKey()+"&apn=com.none.kedadas");
        //"https://domain/?link=your_deep_link&apn=package_name[&amv=minimum_version][&ad=1][&al=android_link][&afl=fallback_link]"
        Log.d("LINKGORRINO",url);






        //anotherRef.child().setValue(email);
        /*
        Intent intent = new Intent (this, CreaChat.class);
        intent.putExtra("kdd_id",kdd1.getId());
        intent.putExtra("kdd_name",kdd1.getNombre());
        startActivity(intent);
        */
    }
}
