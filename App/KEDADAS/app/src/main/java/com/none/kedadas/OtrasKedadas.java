package com.none.kedadas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by diego on 22/03/2017.
 */

public class OtrasKedadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        list = (ListView) findViewById(R.id.listview_listado);
        list.setAdapter(new ListaAdaptador(this, R.layout.element, esegunda){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView1);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Kedada) entrada).toString());

                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Kedada elegido = (Kedada) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + elegido.toString();
                Toast toast = Toast.makeText(MainPage.this, texto, Toast.LENGTH_LONG);
                toast.show();


            }
        });
    }
}
