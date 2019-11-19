package dev.lukeb.a4_android_restclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("username")
    @Expose
    String username;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("website")
    @Expose
    String website;


    public User(String name, String username, String email, int id, String phone, String website){
        this.name = name;
        this.username = username;
        this.email = email;
        this.id = id;
        this.phone = phone;
        this.website = website;
    }

    protected User(Parcel in) {
        name = in.readString();
        username = in.readString();
        email = in.readString();
        id = in.readInt();
        phone = in.readString();
        website = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }

    public void setWebsite(String website) { this.website = website; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeInt(id);
        parcel.writeString(phone);
        parcel.writeString(website);
    }
}
