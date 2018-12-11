package com.altamirano.fabricio.embassies.commons;

import java.util.Date;

public class LastSearch {
    private int _id;
    private Date date;
    private double lon;
    private double lat;

    public Date getDate() {
        return date;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int get_id() {
        return _id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
