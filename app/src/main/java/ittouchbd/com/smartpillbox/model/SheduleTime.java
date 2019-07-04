package ittouchbd.com.smartpillbox.model;

public class SheduleTime {
    private int hour;
    private  int minute;

    public SheduleTime() {
    }

    public SheduleTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTimeString(){
        return this.hour + ":" + this.minute;
    }
}
