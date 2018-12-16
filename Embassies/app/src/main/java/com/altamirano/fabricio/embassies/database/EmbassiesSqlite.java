package com.altamirano.fabricio.embassies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.altamirano.fabricio.embassies.commons.LastSearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EmbassiesSqlite {

    public static EmbassiesSqlite instance;

    private Context ctx;
    private SQLiteDatabase db;

    public EmbassiesSqlite(Context ctx) {
        this.ctx = ctx;
        this.db = new EmbassiesSqliteHelper(this.ctx).getWritableDatabase();
    }

    public Context getCtx() {
        return ctx;
    }

    private void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public static EmbassiesSqlite getInstance(Context ctx) {
        if (instance == null) {
            instance = new EmbassiesSqlite(ctx);
        }
        return instance;
    }

    public ArrayList<LastSearch> getLastSearchs() {
        ArrayList<LastSearch> result = new ArrayList<>();
        try {
            Cursor cr = this.db.rawQuery("Select * from " + Table_LastSearch.TABLE_NAME+" ORDER BY "+Table_LastSearch.DATE +" DESC ", null);
            if (cr != null && cr.moveToNext()) {
                do {
                    result.add(Table_LastSearch.getFromCursor(cr));
                } while (cr.moveToNext());
                cr.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void upsert(LastSearch lastSearch) {

        Cursor cr = this.db.rawQuery("Select * from " + Table_LastSearch.TABLE_NAME + " WHERE _id=" + lastSearch.get_id(), null);
        if (cr != null && cr.moveToNext()) {
            LastSearch item = Table_LastSearch.getFromCursor(cr);
            lastSearch.set_id(item.get_id());
            this.update(lastSearch);
            cr.close();
        } else {
            this.insert(lastSearch);
        }
    }

    private Date getDateFromSql(String date) {

        if (date == null || date.length() == 0) {
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

    private String getDateToSql(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        return sdf.format(date);
    }

    private void insert(LastSearch search) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Table_LastSearch.ID, search.get_id());

        values.put(Table_LastSearch.LAT, search.getLat());
        values.put(Table_LastSearch.LON, search.getLon());
        values.put(Table_LastSearch.DATE, this.getDateToSql(search.getDate()));

        values.put(Table_LastSearch.Title, search.getTitle());
        values.put(Table_LastSearch.Locality, search.getLocality());
        values.put(Table_LastSearch.Streetaddress, search.getStreetaddress());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Table_LastSearch.TABLE_NAME, null, values);
        search.set_id((int) newRowId);
    }

    private void update(LastSearch search) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Table_LastSearch.ID, search.get_id());

        values.put(Table_LastSearch.LAT, search.getLat());
        values.put(Table_LastSearch.LON, search.getLon());
        values.put(Table_LastSearch.DATE, this.getDateToSql(search.getDate()));

        values.put(Table_LastSearch.Title, search.getTitle());
        values.put(Table_LastSearch.Locality, search.getLocality());
        values.put(Table_LastSearch.Streetaddress, search.getStreetaddress());

        // Insert the new row, returning the primary key value of the new row
        String whereClause = "_id=" + search.get_id();
        long newRowId = db.update(Table_LastSearch.TABLE_NAME, values, whereClause, null);
        search.set_id((int) newRowId);
    }
}
