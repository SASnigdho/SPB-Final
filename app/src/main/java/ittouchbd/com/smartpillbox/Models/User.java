package ittouchbd.com.smartpillbox.Models;

import java.util.List;

public class User{
    private String Id;
    private String Name;
    private String Email;
    private String Password;
    private String PhoneNumber;
    private int Age;
    private String Gender;
    private String BloodGroup;

    private List<Prescription> prescriptions;

    public User() {
    }

    public User(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }

    public User(String id, String name, String email, String password, String phoneNumber, int age, String gender, String bloodGroup, List<Prescription> prescriptions) {
        Id = id;
        Name = name;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
        Age = age;
        Gender = gender;
        BloodGroup = bloodGroup;
        this.prescriptions = prescriptions;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}