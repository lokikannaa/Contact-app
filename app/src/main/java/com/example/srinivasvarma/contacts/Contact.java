package com.example.srinivasvarma.contacts;

import android.graphics.Bitmap;
import android.media.Image;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.Date;

/**
 * Created by Lokesh on 14/09/2017.
 */

public class Contact implements Serializable {

    private String profilePic;
    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private String email;
    private String URL;
    private String address;
    private Date birthday;
    private String nickName;
    private String fbURL;
    private String twitterURL;
    private String skype;
    private String youtube;

    public Contact(String profilePic, String firstName, String lastName, String company, String phone, String email, String URL, String address, Date birthday, String nickName, String fbURL, String twitterURL, String skype, String youtube) {
        this.profilePic = profilePic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.URL = URL;
        this.address = address;
        this.birthday = birthday;
        this.nickName = nickName;
        this.fbURL = fbURL;
        this.twitterURL = twitterURL;
        this.skype = skype;
        this.youtube = youtube;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFbURL() {
        return fbURL;
    }

    public void setFbURL(String fbURL) {
        this.fbURL = fbURL;
    }

    public String getTwitterURL() {
        return twitterURL;
    }

    public void setTwitterURL(String twitterURL) {
        this.twitterURL = twitterURL;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
