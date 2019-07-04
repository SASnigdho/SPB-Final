package ittouchbd.com.smartpillbox.model;

public class UserInfo {
    private String userId;
    private String name;
    private String mobileNumber;
    private String gender;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String name, String mobileNumber, String gender, String password) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.password = password;
    }

    public UserInfo(String userId, String name, String mobileNumber, String gender, String password) {
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
