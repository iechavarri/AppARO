package com.none.kedadas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by diego on 15/03/2017.
 */

public class MainPage extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

    }

    public void toNuevaKedada(View view){

        Intent intent = new Intent(this, NuevaKedada.class);
        startActivity(intent);

    }

    public void onClick(View view){

        /*Intent intent = new Intent(this, OtrasKedadas.class);
        startActivity(intent);*/
        Intent intent = new Intent (this, MasKedadas.class);
        startActivity(intent);

    }

    public void toConfiguracion(View view){

        Intent intent = new Intent(this, Configuracion.class);
        startActivity(intent);
    }

    public void toAyuda(View view){

        Intent intent = new Intent(this, Ayuda.class);
        startActivity(intent);
    }

}
