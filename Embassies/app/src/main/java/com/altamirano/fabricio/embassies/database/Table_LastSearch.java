package com.altamirano.fabricio.embassies.database;

import android.database.Cursor;

import com.altamirano.fabricio.embassies.commons.LastSearch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Table_LastSearch {

    public static final String ID="_id";
    public static final String Streetaddress="Streetaddress";
    public static final String Locality="Locality";
    public static final String Title="Title";
    public static final String LON="LON";
    public static final String LAT="LAT";
    public static final String DATE="DATE";
    public static final String TABLE_NAME="LastSearch";


    public static final String CREATE_TABLE = "CREATE TABLE '"+TABLE_NAME+"' (" +
            "'_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "'DATE' DATE," +
            "'Streetaddress' VARCHAR," +
            "'Locality' VARCHAR," +
            "'Title' VARCHAR," +
            "'LON' NUMERIC, " +
            "'LAT' NUMERIC" +
            ");";

    public static LastSearch getFromCursor(Cursor cr){
        LastSearch item = new LastSearch();
        item.set_id(cr.getInt(0));
        item.setDate(getDateFromSql(cr.getString(1)));
        item.setStreetaddress(cr.getString(2));
        item.setLocality(cr.getString(3));
        item.setTitle(cr.getString(4));
        item.setLon(cr.getDouble(5));
        item.setLat(cr.getDouble(6));
        return item;
    }

    public static Date getDateFromSql(String date) {

        if (date == null || date.length()==0) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            Date d = sdf.parse(date);
            return d;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
