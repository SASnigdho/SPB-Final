package ittouchbd.com.smartpillbox.activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.helper.AlarmHelper;
import ittouchbd.com.smartpillbox.model.MedicineInfo;

public class AlarmActivity extends AppCompatActivity {

    //FireBase
    private FirebaseDatabase firebaseDatabase;

    private TextView medicineNames;
    private Button snoozeBT, okBT;

    private String prescriptionId;
    private int hour;
    private int minute;
    private int reqCode;
    private String medicineName;

    private MediaPlayer mediaPlayer;
    private MedicineInfo medicineInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        initialize();
    }

    private void initialize() {
        initializeBasicVariable();
        initializeDesign();
        loadMedicineInfo();
        notifyDevice();
    }


    private void initializeBasicVariable() {

        firebaseDatabase = FirebaseDatabase.getInstance();

        prescriptionId = getIntent().getStringExtra("key");
        hour = Integer.valueOf(getIntent().getStringExtra("hour"));
        minute = Integer.valueOf(getIntent().getStringExtra("minute"));
        reqCode = Integer.valueOf(getIntent().getStringExtra("reqCode"));
        medicineName = getIntent().getStringExtra("medicineName");
    }

    private void initializeDesign() {
        medicineNames = findViewById(R.id.aa_medicineName_tv_Id);
        medicineNames.setText(medicineName);

        snoozeBT = findViewById(R.id.aa_snooze_btn_Id);
        okBT = findViewById(R.id.aa_ok_btn_Id);

        snoozeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snooze();
                Toast.makeText(AlarmActivity.this, "Snooze For 10 Minute", Toast.LENGTH_SHORT).show();
            }
        });

        okBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMedicineStatus();
                stopDeviceAlarm();
                finish();
            }
        });
    }

    private void stopDeviceAlarm() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("ArduinoAlarm");
        userDB.child("Alarm").setValue("false");
    }

    private void loadMedicineInfo() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList").child(prescriptionId).child("medicineInfo");

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    medicineInfo = dataSnapshot.getValue(MedicineInfo.class);
                } else {
                    Toast.makeText(AlarmActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void notifyDevice() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("ArduinoAlarm");
        userDB.child("Alarm").setValue("true");
        userDB.child("hour").setValue(hour);
        userDB.child("minute").setValue(minute);
        userDB.child("medicineNmae").setValue(medicineName);
    }

    private void updateMedicineStatus() {
        int quantityPerDose = medicineInfo.getQuantityPerDose();

        int alreadyIntake = medicineInfo.getAlreadyIntake();
        int dueIntake = medicineInfo.getDueIntake();
        int currentStock = medicineInfo.getCurrentStock();

        int newAlreadyIntake = alreadyIntake + quantityPerDose;
        int newDueIntake = dueIntake - quantityPerDose;
        int newCurrentStock = currentStock - quantityPerDose;

        sendUpdateValueToFireBase(newAlreadyIntake, newDueIntake, newCurrentStock);
    }

    private void sendUpdateValueToFireBase(int newAlreadyIntake, int newDueIntake, int newCurrentStock) {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("prescriptionList").child(prescriptionId).child("medicineInfo");
        userDB.child("alreadyIntake").setValue(newAlreadyIntake);
        userDB.child("dueIntake").setValue(newDueIntake);
        userDB.child("currentStock").setValue(newCurrentStock);
    }

    private void snooze() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calenderObject = getCalenderObject(hour, minute);

        Intent intent = new Intent(getApplicationContext(), AlarmHelper.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), reqCode, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calenderObject.getTimeInMillis() + 10 * 1000, pendingIntent);

    }

    private Calendar getCalenderObject(int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        return c;
    }

//    private void playMusic() {
//
//        if (mediaPlayer != null) {
//            mediaPlayer = MediaPlayer.create(this, R.raw.let_me_love_you);
//        }
//        assert mediaPlayer != null;
//        mediaPlayer.start();
//    }
//
//    private void stopMusic() {
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }
}
