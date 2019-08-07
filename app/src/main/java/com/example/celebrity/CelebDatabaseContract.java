package com.example.celebrity;

import java.util.Locale;

public class CelebDatabaseContract {

    public static final String DATABASE_NAME = "celebs_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Celebs_table";
    public static final String COL_FIRST_NAME = "first";
    public static final String COL_LAST_NAME = "last";

    public static final String DROP_TABLE_QUERY = "DROP TABLE " + TABLE_NAME;//Delete the entire table
    public static final String SELECT_ALL_CELEBS = "SELECT * FROM "+TABLE_NAME;

    public static String getCreateTableQuery(){
        return String.format(Locale.US, "CREATE TABLE %s( %s TEXT PRIMARY_KEY, %s TEXT)",
        TABLE_NAME, COL_FIRST_NAME, COL_LAST_NAME);
    }

    public static String getSelectCelebByFirstNameQuery(String firstName){
        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \'%s\'", TABLE_NAME, firstName);

    }
}
