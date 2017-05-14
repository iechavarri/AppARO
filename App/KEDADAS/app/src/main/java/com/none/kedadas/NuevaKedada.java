package com.none.kedadas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by diego on 22/03/2017.
 */

public class NuevaKedada extends AppCompatActivity {
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;
    private DateFormat dateFormatter;
    private Calendar c;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_kedada);
        c = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        setCurrentDateOnView();
        addListenerOnButton();
    }

    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);

        // set current date into textview
        tvDisplayDate.setText(
                dateFormatter.format(c.getTime())
                /*new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("/").append(month+1).append("/")
                .append(year).append(" ")
                */);
    }
    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            c.set(Calendar.YEAR, selectedYear);
            c.set(Calendar.MONTH, selectedMonth);
            c.set(Calendar.DAY_OF_MONTH, selectedDay);

            // set selected date into textview
            tvDisplayDate.setText(dateFormatter.format(c.getTime()));

            // set selected date into datepicker also
            //dpResult.init(year, month, day, null);

        }
    };




    public void crearChat(View view) throws ParseException {
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
        //EditText kdddate_edit = (EditText) findViewById(R.id.add_kdddate_edittext);

        //String kdddate = kdddate_edit.getText().toString();
        String kdddate = tvDisplayDate.getText().toString();
       // String kdddate = day+"/"+month+"/"+year;
        if(TextUtils.isEmpty(kdddate)) {
            //kdddate_edit.setError("Debes introducir una fecha de KDD");
            return;
        }



        //This is for take the actual user data
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            userToAdd= new Kedada.Users(user.getUid(),user.getEmail(),"42.3","42.3");

            //userGroup = new Kedada.Users(user.getUid(),user.getEmail(),"42.3","42.3");

        }
        Kedada kdd1 = new Kedada(kddname, c.getTime()/*dateFormatter.parse(kdddate)*/,userToAdd);
        kdd1.AddUser(new Kedada.Users("This is a fucking id","AndThisAnd@email.com","12.2","12.2"));
        DatabaseReference anotherRef;
        anotherRef = myRef.push();
        kdd1.setId(anotherRef.getKey());
        anotherRef.setValue(kdd1);
        anotherRef.child("users").push().getKey();

        //TODO Creating deeplink for the new kedada

        //In this section the new link will be created
        //https://yhj7b.app.goo.gl/?link=https://yhj7b.app.goo.gl/&apn=com.none.kedadas&afl=http://52.43.198.218
        //String url = new String("https://yhj7b.app.goo.gl/"+"?link="+anotherRef.getKey()+"&apn=com.none.kedadas");
        String url = new String("https://yhj7b.app.goo.gl/?link="+
                "https://yhj7b.app.goo.gl/?KedadaKey="+anotherRef.getKey()+"&apn="+"" +
                "com.none.kedadas&afl=http://52.43.198.218");
        //This is going to be autocopied to clipboard
        ClipData clip = ClipData.newPlainText(url,url);
        ClipboardManager clipboard = (ClipboardManager)this.getSystemService(CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "URL copiada al portapapeles", Toast.LENGTH_LONG).show();



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



