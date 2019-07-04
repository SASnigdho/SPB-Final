package ittouchbd.com.smartpillbox.model;

public class AccountInfo {
    private String Id;
    private String Name;
    private String Number;
    private int Age;
    private String BloodGroup;
    private String Gender;


    public AccountInfo() {
    }

    public AccountInfo(String name, String number, int age, String bloodGroup, String gender) {
        Name = name;
        Number = number;
        Age = age;
        BloodGroup = bloodGroup;
        Gender = gender;
    }

    public AccountInfo(String id, String name, String number, int age, String bloodGroup, String gender) {
        Id = id;
        Name = name;
        Number = number;
        Age = age;
        BloodGroup = bloodGroup;
        Gender = gender;
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

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
