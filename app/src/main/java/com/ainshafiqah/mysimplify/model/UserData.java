package com.ainshafiqah.mysimplify.model;

public class UserData {
    String email;
    String phoneNum;
    String name;
    String companyName;
    String itemName;

    public UserData(String email, String phoneNum, String name, String companyName, String itemName) {
        this.email = email;
        this.phoneNum = phoneNum;
        this.name = name;
        this.companyName = companyName;
        this.itemName = itemName;
    }

    public UserData(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
