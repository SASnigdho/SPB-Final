package ittouchbd.com.smartpillbox.fragments;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import ittouchbd.com.smartpillbox.MainActivity;
import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.adapters.SheduleAdapter;
import ittouchbd.com.smartpillbox.helper.AlarmHelper;
import ittouchbd.com.smartpillbox.helper.ChangeFragment;
import ittouchbd.com.smartpillbox.model.AppointmentInfo;
import ittouchbd.com.smartpillbox.model.DoctorInfo;
import ittouchbd.com.smartpillbox.model.MedicineInfo;
import ittouchbd.com.smartpillbox.model.PrescriptionInfo;
import ittouchbd.com.smartpillbox.model.SheduleTime;

public class InputPrescriptionFM extends Fragment {

    private View view;

    //Layout Declare
    private LinearLayout dLayout, apLayout, mLayout;

    //Doctor Info Layout Field Declare
    private EditText doctorNameET, doctorSpecialityET, doctorNumberET, doctorChamberET, doctorEmailET, doctorNoteET;

    //Appointment Date Layout Field Declare
    private Button nextAppointmentDateBT, testResultDateBT;
    private EditText testInfoET;

    // Medicine Info Layout Field Declare
    private EditText medicineNameET, medicineTypeET, medicineStrengthET, totalDaysET, quantityPerDoseET, intakeTimePerDayET;
    private Button startDateBT, setScheduleBT;

    //Object Declare
    private DoctorInfo doctorInfo;
    private AppointmentInfo appointmentInfo;
    private MedicineInfo medicineInfo;
    private PrescriptionInfo prescriptionInfo;

    private ChangeFragment changeFragment;

    //Some Private Field
    private String date;
    private String nextAppointmentDate;

    //rView
    private ArrayList<SheduleTime> timesList;
    private RecyclerView scheduleRCV;
    private SheduleAdapter sheduleAdapter;

    //Firebase Database
    private FirebaseDatabase firebaseDatabase;

    public InputPrescriptionFM() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_input_prescription, container, false);

        findViewById();

        return view;
    }

    private void findViewById() {
        initializePrescriptionField();
        initializeBasicVariable();
    }

    private void initializeBasicVariable() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        changeFragment = (MainActivity) getActivity();

        //Recycler view
        timesList = new ArrayList<SheduleTime>();
        sheduleAdapter = new SheduleAdapter(timesList);
        scheduleRCV.setLayoutManager(new LinearLayoutManager(getContext()));
        scheduleRCV.setItemAnimator(new DefaultItemAnimator());
        scheduleRCV.setAdapter(sheduleAdapter);
    }

    private void initializePrescriptionField() {
        initializeDoctorInfo();
        initializeAppointmentInfo();
        initializeMedicineInfo();
    }

    private void initializeDoctorInfo() {
        dLayout = view.findViewById(R.id.D_layout_Id);

        doctorNameET = view.findViewById(R.id.apDoctorName_edt_Id);
        doctorNumberET = view.findViewById(R.id.apDoctorNumber_edt_Id);
        doctorChamberET = view.findViewById(R.id.apDoctorChamber_edt_Id);
        doctorEmailET = view.findViewById(R.id.apDoctorEmail_edt_Id);
        doctorNoteET = view.findViewById(R.id.apDoctorNote_edt_Id);
        doctorSpecialityET = view.findViewById(R.id.apDoctorEspecially_edt_Id);

        view.findViewById(R.id.doctorInfoLayout_btn_Id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorInfoNext();
            }
        });
    }

    private void initializeAppointmentInfo() {
        apLayout = view.findViewById(R.id.Ap_layout_Id);

        nextAppointmentDateBT = view.findViewById(R.id.nextApDate_btn_Id);
        testInfoET = view.findViewById(R.id.testInfo_edt_Id);
        testResultDateBT = view.findViewById(R.id.testResultDate_btn_Id);

        nextAppointmentDateBT.setOnClickListener(new View.OnClickListener() {
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

                                nextAppointmentDateBT.setText(dateSet);
                            }
                        }, currentYear, currentMonth, currentDate);
                datePickerDialog.show();
            }

        });


        testResultDateBT.setOnClickListener(new View.OnClickListener() {
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

                                testResultDateBT.setText(dateSet);
                            }
                        }, currentYear, currentMonth, currentDate);
                datePickerDialog.show();
            }
        });

        view.findViewById(R.id.appointmentInfoLayout_btn_Id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentLayoutNext();
            }
        });
    }

    private void initializeMedicineInfo() {
        mLayout = view.findViewById(R.id.M_layout_Id);

        medicineNameET = view.findViewById(R.id.medicineName_edt_Id);
        medicineTypeET = view.findViewById(R.id.medicineType_edt_Id);
        medicineStrengthET = view.findViewById(R.id.medicineStrength_edt_Id);
        totalDaysET = view.findViewById(R.id.medicineTotalDay_edt_Id);
        quantityPerDoseET = view.findViewById(R.id.medicineQuantity_edt_Id);
        intakeTimePerDayET = view.findViewById(R.id.medicineIntakeTimePerDay_edt_Id);

        startDateBT = view.findViewById(R.id.startDay_btn_Id);
        setScheduleBT = view.findViewById(R.id.setSchedule_btn_Id);

        scheduleRCV = view.findViewById(R.id.sechedule_rcv_Id);

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

        setScheduleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePicker timePicker = new TimePicker(getContext());
                int currentHour = timePicker.getCurrentHour();
                int currentMinute = timePicker.getCurrentMinute();

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        SheduleTime sheduleTime = new SheduleTime(hourOfDay,minute);
                        timesList.add(sheduleTime);
                        //setMultipleTimeAlarm(timesList);
                        sheduleAdapter.notifyDataSetChanged();

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        view.findViewById(R.id.mLayout_Save_btn_Id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineInfoSave();
                //setMultipleTimeAlarm(timesList);
                clearPrescriptionField();
                changeFragment.changeFragment(new ViewPrescriptionListFM(), "Prescription List", null);
            }
        });
    }

    private void doctorInfoNext() {

        try {
            getDoctorInfoAndCreateObject();

            dLayout.setVisibility(View.GONE);
            apLayout.setVisibility(View.VISIBLE);

        } catch (Exception e) {

            Toast.makeText(getContext(), "Error: " +e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getDoctorInfoAndCreateObject() {
        String doctorName = doctorNameET.getText().toString().trim();
        String doctorSpeciality = doctorSpecialityET.getText().toString().trim();
        String doctorNumber = doctorNumberET.getText().toString().trim();
        String doctorOffice = doctorChamberET.getText().toString().trim();
        String doctorEmail = doctorEmailET.getText().toString().trim();
        String doctorNote = doctorNoteET.getText().toString().trim();

        doctorInfo = new DoctorInfo(doctorName, doctorSpeciality, doctorNumber, doctorOffice, doctorEmail, doctorNote);
    }

    private void appointmentLayoutNext() {

        try {
            getApInfoAndCreateObject();

            apLayout.setVisibility(View.GONE);
            mLayout.setVisibility(View.VISIBLE);

        } catch (Exception e) {

            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getApInfoAndCreateObject() {
        String nextApDate = nextAppointmentDateBT.getText().toString().trim();
        String testInfo = testInfoET.getText().toString().trim();
        String testResultDate = testResultDateBT.getText().toString().trim();

        appointmentInfo = new AppointmentInfo(nextApDate, testInfo, testResultDate);
    }

    private void medicineInfoSave() {
        try {
            getMedicineInfoAndCreateObject();
            String key = saveToDatabase(new PrescriptionInfo(doctorInfo, appointmentInfo, medicineInfo));
            //if (saveStatus){
                setMultipleTimeAlarm(timesList, key);
                saveStatus = false;
            //}
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void getMedicineInfoAndCreateObject() {
        String medicineName = medicineNameET.getText().toString().trim();
        String medicineType = medicineTypeET.getText().toString().trim();
        String medicineStrength = medicineStrengthET.getText().toString().trim();
        String startDate = startDateBT.getText().toString();
        int totalDayes = Integer.parseInt(totalDaysET.getText().toString().trim());
        int quantityPerDose = Integer.parseInt(quantityPerDoseET.getText().toString().trim());
        int intakePerDay = Integer.parseInt(intakeTimePerDayET.getText().toString().trim());

        medicineInfo = new MedicineInfo(medicineName, medicineType, medicineStrength, startDate, totalDayes, quantityPerDose, intakePerDay, timesList);
    }
    private boolean saveStatus = false;
    //private String prescriptionId=null;
    private String saveToDatabase(PrescriptionInfo prescriptionInfo) {

        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList");

        String key = userDB.push().getKey();
        prescriptionInfo.setID(key);
        assert key != null;
        userDB.child(key).setValue(prescriptionInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Save to fireBase!", Toast.LENGTH_SHORT).show();
                    saveStatus = true;
                } else {
                    saveStatus = false;
                    Toast.makeText(getContext(), "Not Save in FireBase!" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return key;
    }

    private Calendar getCalenderObject(int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        return c;
    }

    public void setAlarm_ahr(ArrayList<Calendar> calendars, String key) {

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        int reqCode = 0;
        for (Calendar calendar:calendars) {
            Intent intent = new Intent(getContext(), AlarmHelper.class)
                    .putExtra("hour", String.valueOf(timesList.get(reqCode).getHour()))
                    .putExtra("minute", String.valueOf(timesList.get(reqCode).getMinute()))
                    .putExtra("reqCode", String.valueOf(reqCode))
                    .putExtra("medicineName", medicineNameET.getText().toString().trim())
                    .putExtra("key", key);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), ++reqCode, intent, 0);

            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

    }

    private void setMultipleTimeAlarm(ArrayList<SheduleTime> timeArrayList, String key){
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();

        for (SheduleTime time:timeArrayList) {
            calendars.add(getCalenderObject(time.getHour(),time.getMinute()));
        }

        setAlarm_ahr(calendars,key);
    }

    private void clearPrescriptionField() {
        clearDoctorField();
        clearAppointmentField();
        clearMedicineField();
    }

    private void clearDoctorField() {
        doctorNameET.setText("");
        doctorSpecialityET.setText("");
        doctorNumberET.setText("");
        doctorChamberET.setText("");
        doctorEmailET.setText("");
        doctorNoteET.setText("");
    }

    private void clearAppointmentField() {
        nextAppointmentDateBT.setText("Next Appointment Date");
        testInfoET.setText("");
        testResultDateBT.setText("Test Report Date");
    }

    private void clearMedicineField() {
        medicineNameET.setText("");
        medicineTypeET.setText("");
        medicineStrengthET.setText("");

        startDateBT.setText("Start Date");

        totalDaysET.setText("");
        quantityPerDoseET.setText("");
        intakeTimePerDayET.setText("");

        setScheduleBT.setText("Set Schedule");
        timesList.clear();
        sheduleAdapter.notifyDataSetChanged();
    }

}
