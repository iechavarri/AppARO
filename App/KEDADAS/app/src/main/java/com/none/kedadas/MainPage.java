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
        setContentView(R.layout.activity_kedadas_list);

    }

    protected void toNuevaKedada(View view){

        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);

    }

    protected void toOtrasKedadas(View view){

        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    protected void toConfiguracion(View view){

        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    protected void toAyuda(View view){

        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

}
