package com.e.helph;

public class Helper {
    private String userId;
    private String userName;
    private    String userDonation;
    private   String userLocation;
    private String userDescription;
    private String userQuantity;

    public Helper() {
    }

    public Helper(String userId,String userName, String userDonation, String userLocation, String userDescription, String userQuantity) {
        this.userId = userId;
        this.userName = userName;
        this.userDonation = userDonation;
        this.userLocation = userLocation;
        this.userDescription = userDescription;
        this.userQuantity = userQuantity;
    }
    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }

    public String getUserDonation() {
        return userDonation;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public String getUserQuantity() {
        return userQuantity;
    }
}
