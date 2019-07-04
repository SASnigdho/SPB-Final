package ittouchbd.com.smartpillbox.model;

public class DoctorInfo {

    private String name;
    private String speciality;
    private String number;
    private String chamber;
    private String email;
    private String note;

    public DoctorInfo(String name, String speciality, String number, String chamber, String email, String note) {
        this.name = name;
        this.speciality = speciality;
        this.number = number;
        this.chamber = chamber;
        this.email = email;
        this.note = note;
    }

    public DoctorInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
