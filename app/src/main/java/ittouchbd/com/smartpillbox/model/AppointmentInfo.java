package ittouchbd.com.smartpillbox.model;

public class AppointmentInfo {

    private String nextApDate;
    private String testNote;
    private String testReportDate;

    public AppointmentInfo(String nextApDate, String testNote, String testReportDate) {
        this.nextApDate = nextApDate;
        this.testNote = testNote;
        this.testReportDate = testReportDate;
    }

    public AppointmentInfo() {
    }

    public String getNextApDate() {
        return nextApDate;
    }

    public void setNextApDate(String nextApDate) {
        this.nextApDate = nextApDate;
    }

    public String getTestNote() {
        return testNote;
    }

    public void setTestNote(String testNote) {
        this.testNote = testNote;
    }

    public String getTestReportDate() {
        return testReportDate;
    }

    public void setTestReportDate(String testReportDate) {
        this.testReportDate = testReportDate;
    }
}
