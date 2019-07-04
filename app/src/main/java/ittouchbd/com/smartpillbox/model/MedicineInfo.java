package ittouchbd.com.smartpillbox.model;

import java.util.List;

public class MedicineInfo {

    private String name;
    private String type;
    private String strength;
    private String startDay;
    private int totalDays;
    private int quantityPerDose;
    private int intakeTimePerDay;

    private List<SheduleTime> schedules;

    private int totalIntake;//koy ta khete hobe
    private int alreadyIntake; //koy ta khaichhi
    private int dueIntake; //koyta khawa baki
    private int currentStock; //koyta kinsi
    private int alreadyPurchase; //Koyta Kinsi
    private int duePurchase; //koyta kina baki

    public MedicineInfo() {
    }

    public MedicineInfo(String name, String type, String strength, String startDay, int totalDays, int quantityPerDose, int intakeTimePerDay, List<SheduleTime> schedules) {
        this.name = name;
        this.type = type;
        this.strength = strength;
        this.startDay = startDay;
        this.totalDays = totalDays;
        this.quantityPerDose = quantityPerDose;
        this.intakeTimePerDay = intakeTimePerDay;
        this.schedules = schedules;

        this.totalIntake = totalDays * intakeTimePerDay * quantityPerDose ; // 90 = 30 * 3 * 1
        this.dueIntake = totalIntake;
        this.alreadyIntake = totalIntake - dueIntake; //0 = 90 - 90
        this.dueIntake = totalIntake - alreadyIntake; //90 = 90 - 0
        this.currentStock = 0;
        this.alreadyPurchase = 0;
        this.duePurchase = totalIntake - alreadyPurchase; // 90 = 90 - 0
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getQuantityPerDose() {
        return quantityPerDose;
    }

    public void setQuantityPerDose(int quantityPerDose) {
        this.quantityPerDose = quantityPerDose;
    }

    public int getIntakeTimePerDay() {
        return intakeTimePerDay;
    }

    public void setIntakeTimePerDay(int intakeTimePerDay) {
        this.intakeTimePerDay = intakeTimePerDay;
    }

    public List<SheduleTime> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<SheduleTime> schedules) {
        this.schedules = schedules;
    }

    public int getTotalIntake() {
        return totalIntake;
    }

    public void setTotalIntake(int totalIntake) {
        this.totalIntake = totalIntake;
    }

    public int getAlreadyIntake() {
        return alreadyIntake;
    }

    public void setAlreadyIntake(int alreadyIntake) {
        this.alreadyIntake = alreadyIntake;
    }

    public int getDueIntake() {
        return dueIntake;
    }

    public void setDueIntake(int dueIntake) {
        this.dueIntake = dueIntake;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getAlreadyPurchase() {
        return alreadyPurchase;
    }

    public void setAlreadyPurchase(int alreadyPurchase) {
        this.alreadyPurchase = alreadyPurchase;
    }

    public int getDuePurchase() {
        return duePurchase;
    }

    public void setDuePurchase(int duePurchase) {
        this.duePurchase = duePurchase;
    }
}
