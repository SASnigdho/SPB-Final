package ittouchbd.com.smartpillbox.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.adapters.SheduleAdapter;
import ittouchbd.com.smartpillbox.model.AppointmentInfo;
import ittouchbd.com.smartpillbox.model.DoctorInfo;
import ittouchbd.com.smartpillbox.model.MedicineInfo;
import ittouchbd.com.smartpillbox.model.PrescriptionInfo;
import ittouchbd.com.smartpillbox.model.SheduleTime;

public class UpdatePrescriptionFM extends Fragment {

    private View view;
    //FireBase
    private FirebaseDatabase firebaseDatabase;

    private EditText dNameET, dSpecialityET, dNumberET, dChamberET, emailET, dNoteET, testNoteET;
    private Button nextApDateBT, testReportDateBT, startDateBT, scheduleBT, upUpdateBT;

    private EditText mNameET, typeET, strengthET, totalDaysET, quantityPerDoseET, intakePerDayET;

    //Object
    private PrescriptionInfo updatePrescriptionInfo;

    //Some Private Field
    private String prescriptionId;

    private ArrayList<SheduleTime> times;
    private RecyclerView recyclerView;
    private SheduleAdapter sheduleAdapter;


    public UpdatePrescriptionFM() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_update_prescription, container, false);

        //View Component Initialize
        initializeComponent();

        return view;
    }

    private void initializeComponent() {
        initializeBasicVariable();
        //Get Prescription Data
        getPrescription(prescriptionId);
        initializeRecyclerView();

        initializePrescriptionField();
    }

    private void initializePrescriptionField() {
        initializeDoctorField();
        initializeAppointmentField();
        initializeMedicineInfo();
    }

    private void initializeBasicVariable() {
        firebaseDatabase = FirebaseDatabase.getInstance();

        Bundle bundle = getArguments();
        prescriptionId = bundle.getString("prescriptionId");

        //initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        recyclerView = view.findViewById(R.id.up_schedule_rcv_Id);

        times = new ArrayList<SheduleTime>();
        sheduleAdapter = new SheduleAdapter(times);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sheduleAdapter);
    }

    private void initializeDoctorField() {
        //Doctor Details
        dNameET = view.findViewById(R.id.up_dName_edt_Id);
        dSpecialityET = view.findViewById(R.id.up_dSpeciality_edt_Id);
        dNumberET = view.findViewById(R.id.up_dNumber_edt_Id);
        dChamberET = view.findViewById(R.id.up_dChamber_edt_Id);
        emailET = view.findViewById(R.id.up_dEmail_edt_Id);
        dNoteET = view.findViewById(R.id.up_dNote_edt_Id);
    }

    private void initializeAppointmentField() {

        nextApDateBT = view.findViewById(R.id.up_nextApDate_btn_Id);
        nextApDateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker datePicker = new DatePicker(getContext());

                int currentDate = datePicker.getDayOfMonth();
                int currentMonth = datePicker.getMonth() + 1;
                int currentYear = datePicker.getYear();


                DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()),

                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                String dateSet = month + "/" + dayOfMonth + "/" + year;

                                nextApDateBT.setText(dateSet);
                            }
                        }, currentYear, currentMonth, currentDate);
                datePickerDialog.show();
            }
        });

        testNoteET = view.findViewById(R.id.up_testNote_edt_Id);

        testReportDateBT = view.findViewById(R.id.up_testReportDate_btn_Id);
        testReportDateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker datePicker = new DatePicker(getContext());

                int currentDate = datePicker.getDayOfMonth();
                int currentMonth = datePicker.getMonth() + 1;
                int currentYear = datePicker.getYear();


                DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()),

                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                String dateSet = month + "/" + dayOfMonth + "/" + year;

                                testReportDateBT.setText(dateSet);
                            }
                        }, currentYear, currentMonth, currentDate);
                datePickerDialog.show();
            }
        });
    }

    private void initializeMedicineInfo() {
        mNameET = view.findViewById(R.id.up_mName_edt_Id);
        typeET = view.findViewById(R.id.up_mType_edt_Id);
        strengthET = view.findViewById(R.id.up_mStrength_edt_Id);

        startDateBT = view.findViewById(R.id.up_mStartDate_btn_Id);
        startDateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker datePicker = new DatePicker(getContext());

                int currentDate = datePicker.getDayOfMonth();
                int currentMonth = datePicker.getMonth() + 1;
                int currentYear = datePicker.getYear();

                DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()), AlertDialog.THEME_HOLO_LIGHT,

                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                String dateSet = month + "/" + dayOfMonth + "/" + year;

                                startDateBT.setText(dateSet);
                            }
                        }, currentYear, currentMonth, currentDate);
                datePickerDialog.show();
            }
        });

        totalDaysET = view.findViewById(R.id.up_totalDay_edt_Id);
        quantityPerDoseET = view.findViewById(R.id.up_quantityPerDose_edt_Id);
        intakePerDayET = view.findViewById(R.id.up_intakePerDay_edt_Id);

//        recyclerView = view.findViewById(R.id.up_schedule_rcv_Id);

        scheduleBT = view.findViewById(R.id.up_schedule_btn_Id);
        scheduleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePicker timePicker = new TimePicker(getContext());
                int currentHour = timePicker.getCurrentHour();
                int currentMinute = timePicker.getCurrentMinute();

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        SheduleTime sheduleTime = new SheduleTime(hourOfDay,minute);
                        times.add(sheduleTime);
                        sheduleAdapter.notifyDataSetChanged();
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        upUpdateBT = view.findViewById(R.id.up_update_btn_Id);
        upUpdateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDateToEditField();

                DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList").child(prescriptionId);
                userDB.setValue(updatePrescriptionInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Not Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }


    private void getDateToEditField() {
        String dName = dNameET.getText().toString().trim();
        String dSpeciality = dSpecialityET.getText().toString().trim();
        String dNumber = dNumberET.getText().toString().trim();
        String dChamber = dChamberET.getText().toString().trim();
        String dEmail = emailET.getText().toString().trim();
        String dNote = dNoteET.getText().toString().trim();
        DoctorInfo doctorInfo = new DoctorInfo(dName, dSpeciality, dNumber, dChamber, dEmail, dNote);

        String nextApDate = nextApDateBT.getText().toString().trim();
        String testNote = testNoteET.getText().toString().trim();
        String testReportDate = testReportDateBT.getText().toString().trim();
        AppointmentInfo appointmentInfo = new AppointmentInfo(nextApDate, testNote, testReportDate);

        String mName = mNameET.getText().toString().trim();
        String mType = typeET.getText().toString().trim();
        String mStrength = strengthET.getText().toString().trim();
        String startDate = startDateBT.getText().toString().trim();
        int totalDays = Integer.parseInt(totalDaysET.getText().toString().trim());
        int quantityPerDose = Integer.parseInt(quantityPerDoseET.getText().toString().trim());
        int intakePerDay = Integer.parseInt(intakePerDayET.getText().toString().trim());

        MedicineInfo medicineInfo = new MedicineInfo(mName, mType, mStrength, startDate, totalDays, quantityPerDose, intakePerDay, times);

        updatePrescriptionInfo = new PrescriptionInfo(prescriptionId,doctorInfo, appointmentInfo, medicineInfo);
    }

    private void getPrescription(String prescriptionId) {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList").child(prescriptionId);

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    PrescriptionInfo prescriptionInfo = dataSnapshot.getValue(PrescriptionInfo.class);

                    setDataToEditField(prescriptionInfo);

                } else {
                    Toast.makeText(getContext(), "Not Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setDataToEditField(PrescriptionInfo prescriptionInfo) {
        dNameET.setText(prescriptionInfo.getDoctorInfo().getName());
        dSpecialityET.setText(prescriptionInfo.getDoctorInfo().getSpeciality());
        dNumberET.setText(prescriptionInfo.getDoctorInfo().getNumber());
        dChamberET.setText(prescriptionInfo.getDoctorInfo().getChamber());
        emailET.setText(prescriptionInfo.getDoctorInfo().getEmail());
        dNoteET.setText(prescriptionInfo.getDoctorInfo().getNote());

        nextApDateBT.setText(prescriptionInfo.getAppointmentInfo().getNextApDate());
        testNoteET.setText(prescriptionInfo.getAppointmentInfo().getTestNote());
        testReportDateBT.setText(prescriptionInfo.getAppointmentInfo().getTestReportDate());

        mNameET.setText(prescriptionInfo.getMedicineInfo().getName());
        typeET.setText(prescriptionInfo.getMedicineInfo().getType());
        strengthET.setText(prescriptionInfo.getMedicineInfo().getStrength());
        startDateBT.setText(prescriptionInfo.getMedicineInfo().getStartDay());
        totalDaysET.setText(String.valueOf(prescriptionInfo.getMedicineInfo().getTotalDays()));
        quantityPerDoseET.setText(String.valueOf(prescriptionInfo.getMedicineInfo().getQuantityPerDose()));
        intakePerDayET.setText(String.valueOf(prescriptionInfo.getMedicineInfo().getIntakeTimePerDay()));
    }

}
