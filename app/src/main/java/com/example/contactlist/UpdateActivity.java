package com.example.contactlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    String id, family, name, houseNo, street, town, country, postcode, telephone, namecard;
    EditText familyName0, firstName0, houseNumber0, street0, town0, country0, postcode0, telephone0, nameCard0;
    Button update;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        familyName0 = findViewById(R.id.familyName0);
        firstName0 = findViewById(R.id.firstName0);
        houseNumber0 = findViewById(R.id.houseNumber0);
        street0 = findViewById(R.id.street0);
        town0 = findViewById(R.id.town0);
        country0 = findViewById(R.id.country0);
        postcode0 = findViewById(R.id.postcode0);
        telephone0 = findViewById(R.id.telephone0);
        nameCard0 = findViewById(R.id.nameCard0);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        getIntentData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper mDB  = new MyDatabaseHelper(UpdateActivity.this);
                name = firstName0.getText().toString();
                family = familyName0.getText().toString();
                street = street0.getText().toString();
                town = town0.getText().toString();
                mDB.update(id, family, name, town, street, country, houseNo, postcode, telephone, namecard);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 confirmDialog();
            }
        });
    }
    public void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("fam") && getIntent().hasExtra("firs") && getIntent().hasExtra("tow")
         && getIntent().hasExtra("stree") && getIntent().hasExtra("countr") && getIntent().hasExtra("house")
        && getIntent().hasExtra("pin") && getIntent().hasExtra("tele") && getIntent().hasExtra("nameCa")){
            Log.i("Updated Name: ", "Working! ");
            //Getting Data From Intent
            id = getIntent().getStringExtra("id");
            family  = getIntent().getStringExtra("fam");
           name  = getIntent().getStringExtra("firs");
            houseNo  = getIntent().getStringExtra("house");
            street  = getIntent().getStringExtra("stree");
            town  = getIntent().getStringExtra("tow");
            country  = getIntent().getStringExtra("countr");
            postcode  = getIntent().getStringExtra("pin");
            telephone  = getIntent().getStringExtra("tele");
            namecard  = getIntent().getStringExtra("nameCa");

            //Setting Data to textView
            familyName0.setText(family);
            firstName0.setText(name);
            houseNumber0.setText(houseNo);
            street0.setText(street);
           town0.setText(town);
            country0.setText(country);
            postcode0.setText(postcode);
            telephone0.setText(telephone);
            nameCard0.setText(namecard);

        }
        else{
            Log.i("Updated Name: ", "Not Working! ");

        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are You Sure, You Want To Delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper mDB = new MyDatabaseHelper(UpdateActivity.this);
                mDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}