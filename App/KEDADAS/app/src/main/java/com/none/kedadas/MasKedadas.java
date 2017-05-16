package com.none.kedadas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by diego on 23/03/2017.
 */

public class MasKedadas extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    ArrayList<String> kddsUser = new ArrayList<String>();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();


    public class UserInKDD {
        String KDD;
        String value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otras_kedadas);


        final URL[] urlKedada = new URL[1];
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(AppInvite.API)
                .build();

        boolean autoLaunchDeepLink = false;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(
                        new ResultCallback<AppInviteInvitationResult>() {
                            @Override
                            public void onResult(@NonNull AppInviteInvitationResult result) {
                                if (result.getStatus().isSuccess()) {
                                    // Extract deep link from Intent
                                    Intent intent = result.getInvitationIntent();
                                    String deepLink = AppInviteReferral.getDeepLink(intent);
                                    //String invitation = AppInviteReferral.getInvitationId(intent);

                                    Log.d("TENEMOS UNO!!", deepLink);

                                    String[] trozos = deepLink.split("KedadaKey=");
                                    String kedadaKey=trozos[1];
                                    Toast.makeText(getApplicationContext(), kedadaKey, Toast.LENGTH_LONG).show();

                                    //Añadiendo el usuario a la kedada
                                    //FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                    //FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {
                                        Kedada.Users userToAdd = new Kedada.Users();
                                        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("kdds" + "/" + kedadaKey + "/" + "users");
                                        String uid = user.getUid();
                                        userToAdd.setUserEmail(user.getEmail());
                                        userToAdd.setUserId(user.getUid());
                                        // todo: location
                                        userToAdd.setLatitud("56.45");
                                        userToAdd.setLongitud("43.23");
                                        myRef.child(uid).setValue(userToAdd);
                                        // añadir la kdd al usuario
                                        FirebaseDatabase.getInstance().getReference("users" + "/" + uid + "/" + "kdds" + "/" + kedadaKey).setValue(true);
                                    }

                                    FirebaseDatabase getKedadaForNewUser = FirebaseDatabase.getInstance();
                                    DatabaseReference referenceForNewUSer = getKedadaForNewUser.getReference("kedadas-7a35e");
                                    DatabaseReference usRef= referenceForNewUSer.child("kdds").child(kedadaKey).getRef().child("users");
<<<<<<< HEAD

                                
                                    //Log.d("QUE MIERDA",queEs);

                                    // Handle the deep link. For example, open the linked
                                    // content, or apply promotional credit to the user's
                                    // account.

                                    // ...
                                } else {
=======
                   } else {
>>>>>>> 636e816cdf770e864f8b81f1929348c5a85c7519
                                    Log.d("Y EL LINK???", "getInvitation: no deep link found.");
                                }
                            }
                        });




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
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
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
                // TODO: Show only Kedadas that you belong
                ArrayList<String> estasKedadas = inKdd();
                if( estasKedadas.contains(kdd.getId()) ) {
                    adapter.add(kdd);
                }
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
        Intent intent = new Intent(this, Chat.class);
        intent.putExtra("kdd_name", kdd_name);
        intent.putExtra("kdd_id", kdd_id);
        startActivity(intent);
    }

    public ArrayList<String> inKdd() {

        FirebaseDatabase getUsersInKedadas = FirebaseDatabase.getInstance();
        final DatabaseReference refForUserKdds = getUsersInKedadas.getReference("users");
        //DatabaseReference kddsDeUser = referenceForNewUSer.child("users").child(user.getUid()).child("kdds").getRef();
        Log.d("MIERDA",refForUserKdds.toString());
        refForUserKdds.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //TODO conseguir recuperar los datos de las kedadas a las que estan apuntados los usuarios
                //no consigo como coño leerlos

                String value = dataSnapshot.getValue().toString();
                Log.d("This sucks",value);

                //{kdds={-KkFHRwT0cmv2SZ7PPc4=true, -KkF3rRdyp5VhQ7QIoLC=true}}

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });  ;
        return kddsUser;

        //child("users").child(user.getUid());
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
