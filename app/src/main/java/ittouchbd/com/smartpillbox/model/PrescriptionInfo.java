package ittouchbd.com.smartpillbox.model;

public class PrescriptionInfo {
    private String ID;
    private DoctorInfo doctorInfo;
    private AppointmentInfo appointmentInfo;
    private MedicineInfo medicineInfo;

    public PrescriptionInfo() {
    }

    public PrescriptionInfo(DoctorInfo doctorInfo, AppointmentInfo appointmentInfo, MedicineInfo medicineInfo) {
        this.doctorInfo = doctorInfo;
        this.appointmentInfo = appointmentInfo;
        this.medicineInfo = medicineInfo;
    }

    public PrescriptionInfo(String ID, DoctorInfo doctorInfo, AppointmentInfo appointmentInfo, MedicineInfo medicineInfo) {
        this.ID = ID;
        this.doctorInfo = doctorInfo;
        this.appointmentInfo = appointmentInfo;
        this.medicineInfo = medicineInfo;
    }

    public String getId() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public DoctorInfo getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(DoctorInfo doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public AppointmentInfo getAppointmentInfo() {
        return appointmentInfo;
    }

    public void setAppointmentInfo(AppointmentInfo appointmentInfo) {
        this.appointmentInfo = appointmentInfo;
    }

    public MedicineInfo getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(MedicineInfo medicineInfo) {
        this.medicineInfo = medicineInfo;
    }
}
