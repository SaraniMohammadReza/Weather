package ir.MohammadReza.Objects;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.annotation.NonNull;

public class City_Objects {


    private long id;
    private double lat;
    private double lon;
    private String name = "";
    private String countryCode = "";
    private Boolean selected= false;




    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    @NonNull
    @Override
    public String toString() {

        return name.toUpperCase() + "," + countryCode;
    }


    public ContentValues getContentValues() {



        ContentValues cv = new ContentValues();

        cv.put("id", id);
        cv.put("name", name);
        cv.put("lat", lat);
        cv.put("lon", lon);
        cv.put("countryCode", countryCode);
        cv.put("selected", selected?1:0);

        return cv;
    }




    public static  ContentValues createContentValues (long id ,String name, double lat,double lon , String countryCode,boolean selected){

        ContentValues cv = new ContentValues();

        cv.put("id", id);
        cv.put("name", name);
        cv.put("lat", lat);
        cv.put("lon", lon);
        cv.put("countryCode", countryCode);
        cv.put("selected", selected?1:0);

        return cv;

    }

        public static City_Objects fromCursor(Cursor cursor){
            // az roye city mikhaym ye cursor estekhraj konim
            City_Objects city_objects = new City_Objects();
            //baray etminan migim ye id daryarf ke esm khat ine
            city_objects.setId(cursor.getLong(cursor.getColumnIndex("id")));
            city_objects.setName(cursor.getString(cursor.getColumnIndex("name")));
            city_objects.setLat(cursor.getDouble(cursor.getColumnIndex("lat")));
            city_objects.setLon(cursor.getDouble( cursor.getColumnIndex("lon")));
            city_objects.setCountryCode(cursor.getString(cursor.getColumnIndex("countryCode")));
            return city_objects;
    }

}
