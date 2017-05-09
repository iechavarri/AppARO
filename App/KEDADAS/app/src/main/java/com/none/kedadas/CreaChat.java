package com.none.kedadas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;
import java.util.Map;


public class CreaChat extends AppCompatActivity {


    private Button  add_usr;
    private EditText usr_mail;

    private String user, kdd_id, kdd_name;
    private DatabaseReference ref1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creachat);

        add_usr = (Button) findViewById(R.id.btn_add_usr1  );
        usr_mail = (EditText) findViewById(R.id.add_usr_edittext);



        kdd_name = getIntent().getExtras().getString("kdd_name");
        kdd_id = getIntent().getExtras().getString("kdd_id");

        user = usr_mail.getText().toString();

        //Sin esto no tira, pero si está no deja avanzar a la siguiente activity
//????????????????????????????????????????????
        if(TextUtils.isEmpty(user)) {
            usr_mail.setError("Your message");
            return;
        }


        ref1 = FirebaseDatabase.getInstance().getReference("kdds").child(kdd_id).child("usrs");

        //

        add_usr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Map<String,Object> map = new HashMap<String, Object>();
                map.put(user,"");
                ref1.updateChildren(map);


            }
        });

    }



    public void gotoChat(View view){


        Intent intent = new Intent (this, CreaChat.class);
        intent.putExtra("kdd_name",kdd_name);
        intent.putExtra("kdd_id",kdd_id);
        startActivity(intent);
    }
}
