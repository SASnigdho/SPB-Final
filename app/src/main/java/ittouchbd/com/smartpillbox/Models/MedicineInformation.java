package ittouchbd.com.smartpillbox.Models;

import java.util.List;

public class MedicineInformation{
    private String Name;
    private String Type;
    private String Strength;
    private String StartDay;
    private String TotalDay;
    private String QuantityPerDose;
    private String IntakeTimePerDay;
    private List<Schedule> Schedule;

    public MedicineInformation() {
    }

    public MedicineInformation(String name, String type, String strength, String startDay, String totalDay, String quantityPerDose, String intakeTimePerDay, List<Schedule> schedule) {
        Name = name;
        Type = type;
        Strength = strength;
        StartDay = startDay;
        TotalDay = totalDay;
        QuantityPerDose = quantityPerDose;
        IntakeTimePerDay = intakeTimePerDay;
        Schedule = schedule;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStrength() {
        return Strength;
    }

    public void setStrength(String strength) {
        Strength = strength;
    }

    public String getStartDay() {
        return StartDay;
    }

    public void setStartDay(String startDay) {
        StartDay = startDay;
    }

    public String getTotalDay() {
        return TotalDay;
    }

    public void setTotalDay(String totalDay) {
        TotalDay = totalDay;
    }

    public String getQuantityPerDose() {
        return QuantityPerDose;
    }

    public void setQuantityPerDose(String quantityPerDose) {
        QuantityPerDose = quantityPerDose;
    }

    public String getIntakeTimePerDay() {
        return IntakeTimePerDay;
    }

    public void setIntakeTimePerDay(String intakeTimePerDay) {
        IntakeTimePerDay = intakeTimePerDay;
    }

    public List<ittouchbd.com.smartpillbox.Models.Schedule> getSchedule() {
        return Schedule;
    }

    public void setSchedule(List<ittouchbd.com.smartpillbox.Models.Schedule> schedule) {
        Schedule = schedule;
    }
}