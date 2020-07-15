package com.rnavagamuwa.cicd.be.model;

import javax.persistence.*;

/**
 * @author rnavagamuwa
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
    private String fullName;
    private String dob;
    private String city;
    private boolean isVerified;
    @Column(unique = true)
    private String ssn;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String nic) {
        this.ssn = nic;
    }
}
