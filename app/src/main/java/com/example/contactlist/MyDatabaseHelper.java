package com.example.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
//For Deletion, Updation and Creation of Table.
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context  context;
    private static final String DATABASE_NAME = "ContactList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_contactlist";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FAMILY_NAME = "contact_familyName";
    private static final String COLUMN_FIRST_NAME = "contact_firstName";
    private static final String COLUMN_HOUSE_NUMBER = "contact_houseNumber";
    private static final String COLUMN_STREET = "contact_street";
    private static final String COLUMN_TOWN = "contact_town";
    private static final String COLUMN_COUNTRY = "contact_country";
    private static final String COLUMN_POSTCODE = "contact_postcode";
    private static final String COLUMN_TELEPHONE = "contact_telephone";
    private static final String COLUMN_NAME_CARD = "contact_nameCard";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FAMILY_NAME + " TEXT, "
                 + COLUMN_FIRST_NAME + " TEXT, " + COLUMN_HOUSE_NUMBER + " INTEGER, "+ COLUMN_STREET + " TEXT, " + COLUMN_TOWN + " TEXT, "+ COLUMN_COUNTRY + " TEXT, "
                 + COLUMN_POSTCODE + " INTEGER, " + COLUMN_TELEPHONE + " TEXT, " + COLUMN_NAME_CARD + " TEXT);";
         db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);
    }
    public void addBook(String familyName, String firstName, int houseNumber, String street, String town, String country, int postcode, String telephone, String nameCard){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(COLUMN_FAMILY_NAME, familyName);
        c.put(COLUMN_FIRST_NAME, firstName);
        c.put(COLUMN_HOUSE_NUMBER, houseNumber);
        c.put(COLUMN_STREET, street);
        c.put(COLUMN_TOWN, town);
        c.put(COLUMN_COUNTRY, country);
        c.put(COLUMN_POSTCODE, postcode);
        c.put(COLUMN_TELEPHONE, telephone);
        c.put(COLUMN_NAME_CARD, nameCard);

        long res = db.insert(TABLE_NAME, null, c);
        if(res == -1){
            //Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();

            Log.i("Result", "Failed");
        }
        else{
            //Toast.makeText(context, "Added Sucessfully!", Toast.LENGTH_SHORT).show();
            Log.i("Result", "Executed");
        }

    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void update(String id, String family, String name, String town, String street, String country, String houseNo, String postcode, String telephone, String namecard){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(COLUMN_FAMILY_NAME, family);
        c.put(COLUMN_FIRST_NAME, name);
        c.put(COLUMN_HOUSE_NUMBER, houseNo);
        c.put(COLUMN_STREET, street);
        c.put(COLUMN_TOWN, town);
        c.put(COLUMN_COUNTRY, country);
        c.put(COLUMN_POSTCODE, postcode);
        c.put(COLUMN_TELEPHONE, telephone);
        c.put(COLUMN_NAME_CARD, namecard);

       long res = db.update(TABLE_NAME, c, "_id=?", new String[]{id});
       if(res == -1){
           Log.i("Updation", "Failed");
       }
       else {
           Log.i("Updation", "Succeded");

       }

    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(res == -1){
            Log.i("DELETED: ", "SUCCESSFULLY");
        }
        else{
            Log.i("DELETION: " , "FAILED");
        }
    }
    void deleteAll(){
        SQLiteDatabase db  = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


}
