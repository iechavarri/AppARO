package com.none.kedadas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 23/03/2017.
 */

public class MasKedadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otras_kedadas);

        // Get ListView object from xml
        final ListView listView = (ListView) findViewById(R.id.ActiveKDD);
        ArrayList<Kedada> kddList = new ArrayList<Kedada>();

        // Create a new Adapter, suitable for our two=element subitems
        class KedadasBaseAdapter extends BaseAdapter {
            List<Kedada> _kedadas;
            LayoutInflater _li;

            public KedadasBaseAdapter(Context context, List<Kedada> kedadas){
                this._kedadas = kedadas;
                this._li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            public void add(Kedada item) {
                _kedadas.add(item);
                this.notifyDataSetChanged();
            }
            /**
             * Este método devolverá el número de filas que va
             * a contener la lista
             */
            @Override
            public int getCount() {
                return this._kedadas.size();
            }

            /**
             * Este método devolverá un elemento en una
             * posición pasada por parámetro
             */
            @Override
            public Object getItem(int position) {
                return this._kedadas.get(position);
            }

            /**
             * Este método devolverá el identificador
             * de un elemento de una posición pasada por
             * parámetro.
             */
            @Override
            public long getItemId(int position) { return 0;}

            /**
             * Este método será invocado cada vez
             * que se vaya a crear una vista en la posición
             * especificada
             */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Format formatter = new SimpleDateFormat("dd/mm/yyyy");
                TwoLineListItem twoLineListItem;
                if (convertView == null){
                    twoLineListItem = (TwoLineListItem) _li.inflate(android.R.layout.simple_list_item_2, null);
                } else {
                    twoLineListItem = (TwoLineListItem) convertView;
                }

                Kedada item = this._kedadas.get(position);
                TextView text1 = twoLineListItem.getText1();
                TextView text2 = twoLineListItem.getText2();

                text1.setText(item.getNombre());
                text2.setText(formatter.format(item.fecha));

                return twoLineListItem;
            }

        }
        /*
                TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        text1.setText(persons.get(position).getName());
        text2.setText("" + persons.get(position).getAge());

        return twoLineListItem;
    }
        */
        final KedadasBaseAdapter adapter = new KedadasBaseAdapter(this,kddList);
        /*
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);*/

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("kdds");


        // Assign a listener to detect changes to the child items
        // of the database reference.
        myRef.addChildEventListener(new ChildEventListener(){

            // This function is called once for each child that exists
            // when the listener is added. Then it is called
            // each time a new child is added.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Kedada kdd = dataSnapshot.getValue(Kedada.class);
                // filter kdd and only add it if it's active
                adapter.add(kdd);
            }

            // This function is called each time a child item is removed.
            public void onChildRemoved(DataSnapshot dataSnapshot) {}/*{
                Kedada kdd = dataSnapshot.getValue(Kedada.class);
                adapter.remove(kdd.toString());
            }
            */
            // The following functions are also required in ChildEventListener implementations.
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName){}
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName){}

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG:", "Failed to read value.", error.toException());
            }

        });
        /*
        // Add items via the Button and EditText at the bottom of the window.
        final EditText text = (EditText) findViewById(R.id.todoText);
        final Button button = (Button) findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Create a new child with a auto-generated ID.
                DatabaseReference childRef = myRef.push();

                // Set the child's data to the value passed in from the text box.
                childRef.setValue(text.getText().toString());

            }
        });
        */
        // Delete items when clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Kedada kdd = (Kedada) listView.getItemAtPosition(position);
                gotoChat(kdd.getNombre(), kdd.getId());
                //Query myQuery = myRef.orderByValue().equalTo((String)
                 //       listView.getItemAtPosition(position));

                /*
                myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                */
            }
        });
    }
    private void gotoChat(String kdd_name, String kdd_id) {
        Intent intent = new Intent (this, Chat.class);
        intent.putExtra("kdd_name",kdd_name);
        intent.putExtra("kdd_id",kdd_id);
        startActivity(intent);
    }

}
