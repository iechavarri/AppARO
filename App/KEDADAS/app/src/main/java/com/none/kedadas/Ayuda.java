package com.none.kedadas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by diego on 22/03/2017.
 */

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
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
