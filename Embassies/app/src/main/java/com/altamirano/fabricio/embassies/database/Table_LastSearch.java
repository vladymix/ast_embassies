package com.altamirano.fabricio.embassies.database;

public class Table_LastSearch {

    public static final String ID="_id";
    public static final String DATE="DATE";
    public static final String LON="LON";
    public static final String LAT="LAT";
    public static final String TABLE_NAME="LastSearch";


    public static final String CREATE_TABLE = "CREATE TABLE '"+TABLE_NAME+"' (" +
            "'_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "'DATE' DATE," +
            "'LON' NUMERIC, " +
            "'LAT' NUMERIC" +
            ");";
}
