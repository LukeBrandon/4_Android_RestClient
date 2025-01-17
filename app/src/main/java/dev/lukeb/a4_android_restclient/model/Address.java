package dev.lukeb.a4_android_restclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {

    @SerializedName("street")
    @Expose
    String street;

    @SerializedName("suite")
    @Expose
    String suite;

    @SerializedName("city")
    @Expose
    String city;

    @SerializedName("zipcode")
    @Expose
    String zipcode;

    @SerializedName("geo")
    @Expose
    Geo geo;

    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    protected Address(Parcel in) {
        street = in.readString();
        suite = in.readString();
        city = in.readString();
        zipcode = in.readString();
        geo = in.readParcelable(Geo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(suite);
        dest.writeString(city);
        dest.writeString(zipcode);
        dest.writeParcelable(geo, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getSuite() { return suite; }

    public void setSuite(String suite) { this.suite = suite; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getZipcode() { return zipcode; }

    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public Geo getGeo() { return geo; }

    public void setGeo(Geo geo) { this.geo = geo; }

}
