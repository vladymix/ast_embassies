package com.altamirano.fabricio.embassies.commons;

import java.io.Serializable;
import java.util.Date;

public class LastSearch implements Serializable {
    private int _id;
    private  Date date;
    private double lon;
    private double lat;
    private String streetaddress;
    private String locality;
    private String title;

    public LastSearch() {
    }

    public Date getDate() {
        return date;
    }

    public double getLat() {
        return lat;
    }

    public String getLocality() {
        return locality;
    }

    public double getLon() {
        return lon;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public String getTitle() {
        return title;
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

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
