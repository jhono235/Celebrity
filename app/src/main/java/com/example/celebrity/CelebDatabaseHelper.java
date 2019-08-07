package com.example.celebrity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.database.sqlite.SQLiteCursorCompat;

import java.util.ArrayList;

import static com.example.celebrity.CelebDatabaseContract.COL_FIRST_NAME;
import static com.example.celebrity.CelebDatabaseContract.COL_LAST_NAME;
import static com.example.celebrity.CelebDatabaseContract.DATABASE_NAME;
import static com.example.celebrity.CelebDatabaseContract.DATABASE_VERSION;
import static com.example.celebrity.CelebDatabaseContract.SELECT_ALL_CELEBS;
import static com.example.celebrity.CelebDatabaseContract.TABLE_NAME;

public class CelebDatabaseHelper extends SQLiteOpenHelper {
    public CelebDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CelebDatabaseContract.getCreateTableQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CelebDatabaseContract.DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase);

    }

    public ArrayList<Celebs> getAllCelebs() {
        SQLiteDatabase readableDataBase = this.getReadableDatabase();
        ArrayList<Celebs> returnCelebList = new ArrayList<>();

        Cursor cursor = readableDataBase.rawQuery(SELECT_ALL_CELEBS, null);

        if (cursor.moveToFirst()) {
            do {
                final String firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME));
                final String lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME));
                Celebs currentCeleb = new Celebs(firstName, lastName);
                returnCelebList.add(currentCeleb);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnCelebList;
    }

    public Celebs getCelebsByFirst(String firstToQuery){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Celebs returnCeleb = null;

        Cursor cursor = readableDatabase.rawQuery(CelebDatabaseContract.getSelectCelebByFirstNameQuery(firstToQuery), null);


        if (cursor.moveToFirst()){
            final String firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME));
            final String lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME));
            returnCeleb = new Celebs(firstName,lastName);
        }
        cursor.close();
        return returnCeleb;
    }

    public void insertCelebIntoDatabase(Celebs celebsToInsert){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FIRST_NAME, celebsToInsert.getFirstName());
        contentValues.put(COL_LAST_NAME, celebsToInsert.getLastName());

        database.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteCeleb(String firstToDelete){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, COL_FIRST_NAME + " = ?", new String[]{firstToDelete});

    }

    public void updateCelebIntoDatabase(Celebs celebToUpdate){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FIRST_NAME, celebToUpdate.getFirstName());
        contentValues.put(COL_LAST_NAME, celebToUpdate.getLastName());

        database.update(TABLE_NAME, contentValues, COL_FIRST_NAME +" = ?", new String[]{celebToUpdate.getFirstName()});
    }
}