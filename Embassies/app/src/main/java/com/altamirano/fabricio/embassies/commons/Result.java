package com.altamirano.fabricio.embassies.commons;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("address")
    Address address;
    @SerializedName("location")
    Location location;
    @SerializedName("organization")
    Organization organization;
    @SerializedName("@id")
    String _id;
    private String id;
    private String relation;
    private String title;
    @SerializedName("@type")
    String _type;


    // Getter Methods

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    // Setter Methods

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getid() {
        return id;
    }

    public String gettype() {
        return _type;
    }

    public void setid(String id) {
        this.id = id;
    }

    public void settype(String type) {
        this._type = type;
    }

    public class Address {
        Area AreaObject;
        District DistrictObject;
        private String locality;
        private String postalcode;
        private String streetaddress;


        // Getter Methods

        public Area getArea() {
            return AreaObject;
        }

        public void setArea(Area areaObject) {
            this.AreaObject = areaObject;
        }

        public District getDistrict() {
            return DistrictObject;
        }

        public void setDistrict(District districtObject) {
            this.DistrictObject = districtObject;
        }

        public String getLocality() {
            return locality;
        }

        // Setter Methods

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }

        public String getStreetaddress() {
            return streetaddress;
        }

        public void setStreetaddress(String streetaddress) {
            this.streetaddress = streetaddress;
        }
    }

    public class Area {
        @SerializedName("@id")
        String id;


        // Getter Methods

        public String getid() {
            return id;
        }

        // Setter Methods

        public void setid(String id) {
            this.id = id;
        }
    }

    public class District {
        @SerializedName("@id")
        String id;
        // Getter Methods

        public String getid() {
            return id;
        }

        // Setter Methods

        public void setid(String id) {
            this.id = id;
        }
    }

    public class Location {
        private float latitude;
        private float longitude;


        // Getter Methods

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        // Setter Methods

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }
    }

    public class Organization {
        private String accesibility;
        private String organizationdesc;
        private String organizationname;
        private String schedule;
        private String services;


        // Getter Methods

        public String getAccesibility() {
            return accesibility;
        }

        public void setAccesibility(String accesibility) {
            this.accesibility = accesibility;
        }

        public String getOrganizationdesc() {
            return organizationdesc;
        }

        public void setOrganizationdesc(String organizationdesc) {
            this.organizationdesc = organizationdesc;
        }

        public String getOrganizationname() {
            return organizationname;
        }

        // Setter Methods

        public void setOrganizationname(String organizationname) {
            this.organizationname = organizationname;
        }

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }

        public String getServices() {
            return services;
        }

        public void setServices(String services) {
            this.services = services;
        }
    }
}
