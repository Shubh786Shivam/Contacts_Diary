package com.example.contactlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recView;
    FloatingActionButton add_button;
    MyDatabaseHelper mDB;
    ArrayList<String> id_List, familyList, nameList, townList, streetList, countryList, houseNumberList, postcodeList, telephoneList, nameCardList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recView = findViewById(R.id.recView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        mDB = new MyDatabaseHelper(MainActivity.this);
        id_List = new ArrayList<>();
        familyList = new ArrayList<>();
        nameList = new ArrayList<>();
        townList = new ArrayList<>();
        streetList = new ArrayList<>();
        countryList = new ArrayList<>();
        houseNumberList = new ArrayList<>();
        postcodeList = new ArrayList<>();
        telephoneList = new ArrayList<>();
        nameCardList = new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this, this, id_List, familyList, nameList,
                townList, streetList, countryList, houseNumberList, postcodeList, telephoneList, nameCardList);
        recView.setAdapter(customAdapter);
        recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = mDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                id_List.add(cursor.getString(0));
                familyList.add(cursor.getString(1));
                nameList.add(cursor.getString(2));
                houseNumberList.add(cursor.getString(3));
                streetList.add(cursor.getString(4));
                townList.add(cursor.getString(5));
                countryList.add(cursor.getString(6));
                postcodeList.add(cursor.getString(7));
                telephoneList.add(cursor.getString(8));
                nameCardList.add(cursor.getString(9));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are You Sure, You Want To Delete Everything?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper mDB = new MyDatabaseHelper(MainActivity.this);
                mDB.deleteAll();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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