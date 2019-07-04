package ittouchbd.com.smartpillbox.Models;

public class Schedule{
    private int Hour;
    private int Min;
    private int Second;

    public Schedule() {
    }

    public Schedule(int hour, int min) {
        Hour = hour;
        Min = min;
    }

    public Schedule(int hour, int min, int second) {
        Hour = hour;
        Min = min;
        Second = second;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getMin() {
        return Min;
    }

    public void setMin(int min) {
        Min = min;
    }

    public int getSecond() {
        return Second;
    }

    public void setSecond(int second) {
        Second = second;
    }
}