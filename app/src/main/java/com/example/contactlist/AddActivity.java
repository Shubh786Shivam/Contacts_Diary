package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText familyName, firstName, town, street, country, houseNumber, postcode, telephone, nameCard;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        familyName = findViewById(R.id.familyName);
        firstName = findViewById(R.id.firstName);
        town = findViewById(R.id.town);
        street = findViewById(R.id.street);
        country = findViewById(R.id.country);
        houseNumber = findViewById(R.id.houseNumber);
        postcode = findViewById(R.id.postcode);
        telephone = findViewById(R.id.telephone);
        nameCard = findViewById(R.id.nameCard);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper mDB = new MyDatabaseHelper(AddActivity.this);
                mDB.addBook(familyName.getText().toString().trim(), firstName.getText().toString().trim(), Integer.valueOf(houseNumber.getText().toString().trim()), street.getText().toString().trim(), town.getText().toString().trim(),
                        country.getText().toString().trim(),
                        Integer.valueOf(postcode.getText().toString().trim()), telephone.getText().toString().trim(), nameCard.getText().toString().trim());
            }
        });

    }
}