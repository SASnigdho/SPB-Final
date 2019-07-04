package ittouchbd.com.smartpillbox.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.model.PrescriptionInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPrescriptionFM extends Fragment {

    private TextView doctorNameTV, doctorSpecialityTV, doctorNumberTV, doctorChamberTV, doctorEmailTV, doctorNoteTV;
    private TextView nextApDateTV, testNoteTV, testReportDateTV;
    private TextView medicineNameTV, medicineTypeTV, medicineStrengthTV, medicineStartDateTV, medicineTotalDaysTV, quantityPerDoseTV, intakePerDayTV;

    //Firebase
    private FirebaseDatabase firebaseDatabase;


    private PrescriptionInfo prescriptionInfo;

    String prescriptionID;

    public ViewPrescriptionFM() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_prescription, container, false);

        Bundle bundle = getArguments();
        prescriptionID = bundle.getString("prescriptionId");

        initialize(view);


        loadDataFromFirebase();

        return view;
    }

    private void initialize(View view) {
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Doctor Details
        doctorNameTV = view.findViewById(R.id.VPD_DName_tv_Id);
        doctorSpecialityTV = view.findViewById(R.id.VPD_DSpeciality_tv_Id);
        doctorNumberTV = view.findViewById(R.id.VPD_DNumber_tv_Id);
        doctorChamberTV = view.findViewById(R.id.VPD_DChamber_tv_Id);
        doctorEmailTV = view.findViewById(R.id.VPD_DEmail_tv_Id);
        doctorNoteTV = view.findViewById(R.id.VPD_DNote_tv_Id);
        //Appointment Details
        nextApDateTV = view.findViewById(R.id.VPD_NextApDate_tv_Id_);
        testNoteTV = view.findViewById(R.id.VPD_testNote_tv_Id);
        testReportDateTV = view.findViewById(R.id.VPD_testReportDate_tv_Id);
        //Medicine Details
        medicineNameTV = view.findViewById(R.id.VPD_medicineName_tv_Id);
        medicineTypeTV = view.findViewById(R.id.VPD_medicineType_tv_Id);
        medicineStrengthTV = view.findViewById(R.id.VPD_medicineStrength_tv_Id);
        medicineStartDateTV = view.findViewById(R.id.VPD_medicineStartDate_tv_Id);
        medicineTotalDaysTV = view.findViewById(R.id.VPD_totalDays_tv_Id);
        quantityPerDoseTV = view.findViewById(R.id.VPD_quantityPerDose_tv_Id);
        intakePerDayTV = view.findViewById(R.id.VPD_intakePerDay_tv_Id);
    }

    private void loadDataFromFirebase() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList").child(prescriptionID);

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    prescriptionInfo = dataSnapshot.getValue(PrescriptionInfo.class);

                    assert prescriptionInfo != null;
                    setDataToPrescriptionView(prescriptionInfo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setDataToPrescriptionView(PrescriptionInfo prescriptionInfo) {

        //Doctor Details Set Start
        doctorNameTV.setText(String.format("Name: %s", prescriptionInfo.getDoctorInfo().getName()));
        doctorSpecialityTV.setText(String.format("Speciality: %s", prescriptionInfo.getDoctorInfo().getSpeciality()));
        doctorNumberTV.setText(String.format("Number: %s", prescriptionInfo.getDoctorInfo().getNumber()));
        doctorChamberTV.setText(String.format("Chamber: %s", prescriptionInfo.getDoctorInfo().getChamber()));
        doctorEmailTV.setText(String.format("Email: %s", prescriptionInfo.getDoctorInfo().getEmail()));
        doctorNoteTV.setText(String.format("Note: %s", prescriptionInfo.getDoctorInfo().getNote()));
        //Doctor Details set End

        //Appointment Details Set Start
        nextApDateTV.setText(String.format("Next Appointment Date: %s", prescriptionInfo.getAppointmentInfo().getNextApDate()));
        testNoteTV.setText(String.format("Test Note: %s", prescriptionInfo.getAppointmentInfo().getTestNote()));
        testReportDateTV.setText(String.format("Report Date%s", prescriptionInfo.getAppointmentInfo().getTestReportDate()));
        //Appointment Details Set End

        //Medicine Details Set Start
        medicineNameTV.setText(String.format("Name: %s", prescriptionInfo.getMedicineInfo().getName()));
        medicineTypeTV.setText(String.format("Type: %s", prescriptionInfo.getMedicineInfo().getType()));
        medicineStrengthTV.setText(String.format("Strength: %s", prescriptionInfo.getMedicineInfo().getStrength()));
        medicineStartDateTV.setText(String.format("Start Date: %s", prescriptionInfo.getMedicineInfo().getStartDay()));
        medicineTotalDaysTV.setText(String.format("Total Days: %s", prescriptionInfo.getMedicineInfo().getTotalDays()));
        quantityPerDoseTV.setText(String.format("Quantity Per Dose: %s", prescriptionInfo.getMedicineInfo().getQuantityPerDose()));
        intakePerDayTV.setText(String.format("Intake Per Day: %s", prescriptionInfo.getMedicineInfo().getIntakeTimePerDay()));
        //Medicine Details Set End
    }

}
