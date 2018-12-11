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

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public Date getDateFromSql(String date) {

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

    public String getDateToSql(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        return sdf.format(date);
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
            Cursor cr = this.db.rawQuery("Select * from " + Table_LastSearch.TABLE_NAME, null);
            if (cr != null && cr.moveToNext()) {
                do {
                    LastSearch item = new LastSearch();
                    item.set_id(cr.getInt(0));
                    item.setDate(this.getDateFromSql(cr.getString(1)));
                    item.setLat(cr.getDouble(2));
                    item.setLon(cr.getDouble(3));
                    result.add(item);
                } while (cr.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void insert(LastSearch search) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Table_LastSearch.LAT, search.getLat());
        values.put(Table_LastSearch.LON, search.getLon());
        values.put(Table_LastSearch.DATE, this.getDateToSql(search.getDate()));
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Table_LastSearch.TABLE_NAME, null, values);
        search.set_id((int) newRowId);
    }
}
