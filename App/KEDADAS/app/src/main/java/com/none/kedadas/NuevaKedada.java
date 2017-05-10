package com.none.kedadas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        Kedada kdd1 = new Kedada(kddname,dateFormatter.parse(kdddate));

        DatabaseReference anotherRef;
        anotherRef = myRef.push();
        kdd1.setId(anotherRef.getKey());
        anotherRef.setValue(kdd1);

        anotherRef.child("usrs");

        /*
        Intent intent = new Intent (this, CreaChat.class);
        intent.putExtra("kdd_id",kdd1.getId());
        intent.putExtra("kdd_name",kdd1.getNombre());
        startActivity(intent);
        */
    }
}
