package ittouchbd.com.smartpillbox.Models;

import java.util.List;

public class Prescription{
    private DoctorInformation doctorInformation;
    private List<MedicineInformation> MedicineInformations;

    public Prescription(DoctorInformation doctorInformation, List<MedicineInformation> medicineInformations) {
        this.doctorInformation = doctorInformation;
        MedicineInformations = medicineInformations;
    }

    public Prescription() {
    }

    public DoctorInformation getDoctorInformation() {
        return doctorInformation;
    }

    public void setDoctorInformation(DoctorInformation doctorInformation) {
        this.doctorInformation = doctorInformation;
    }

    public List<MedicineInformation> getMedicineInformations() {
        return MedicineInformations;
    }

    public void setMedicineInformations(List<MedicineInformation> medicineInformations) {
        MedicineInformations = medicineInformations;
    }
}