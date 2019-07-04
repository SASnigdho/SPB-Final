package ittouchbd.com.smartpillbox.Models;

public class DoctorInformation{
    private String Name;
    private String Chamber;
    private String PhoneNumber;
    private String Email;
    private String Speciality;
    private String Note;

    public DoctorInformation() {
    }

    public DoctorInformation(String name, String chamber, String phoneNumber, String email, String speciality, String note) {
        Name = name;
        Chamber = chamber;
        PhoneNumber = phoneNumber;
        Email = email;
        Speciality = speciality;
        Note = note;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getChamber() {
        return Chamber;
    }

    public void setChamber(String chamber) {
        Chamber = chamber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}