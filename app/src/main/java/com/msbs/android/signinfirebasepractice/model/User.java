package com.msbs.android.signinfirebasepractice.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {

    @PrimaryKey
    @NonNull
    public String userId;
    public String email;
    public String firstname;
//    @Exclude
//    public boolean isAuthenticated;
    @Exclude
    boolean isNew, isCreated;

    /**
     * No args constructor for use in serialization
     *
     * @param user
     */
    public User(String user) {
    }

    //Regular Constructor
    @Ignore
    public User(String email, String firstname) {
        this.email = email;
        this.firstname = firstname;
    }

    //Id Constructor

    public User(String userId, String email, String firstname) {
        this.userId = userId;
        this.email = email;
        this.firstname = firstname;
    }





        public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

        public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

        public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname= firstname;
    }


}
