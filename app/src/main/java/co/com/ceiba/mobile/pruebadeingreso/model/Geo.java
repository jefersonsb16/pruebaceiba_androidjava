package co.com.ceiba.mobile.pruebadeingreso.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Geo extends RealmObject {
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
