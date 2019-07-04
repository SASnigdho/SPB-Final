package ittouchbd.com.smartpillbox.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.model.AccountInfo;

public class UpdateAccountInfoActivity extends AppCompatActivity {

    private EditText nameET, numberET, ageET, bloodGroupET, genderET;

    //FireBase
    private FirebaseDatabase firebaseDatabase;

    //Object
    private AccountInfo accountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account_info);

        initialize();
    }

    private void initialize() {
        initializeBasicVariable();
        initializeView();
        downloadAccountInfo();
    }

    private void initializeBasicVariable() { firebaseDatabase = FirebaseDatabase.getInstance(); }

    private void initializeView() {
        nameET = findViewById(R.id.uai_name_edt_Id);
        numberET = findViewById(R.id.uai_number_edt_Id);
        ageET = findViewById(R.id.uai_age_edt_Id);
        bloodGroupET = findViewById(R.id.uai_bloodGroup_edt_Id);
        genderET = findViewById(R.id.uai_gender_edt_Id);

        findViewById(R.id.uai_save_btn_Id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdateInfoAndCreateObject();
                clearField();
            }
        });
    }

    private void getUpdateInfoAndCreateObject() {
        String name = nameET.getText().toString().trim();
        String number = numberET.getText().toString().trim();
        int age = Integer.parseInt(ageET.getText().toString().trim());
        String bloodGroup = bloodGroupET.getText().toString().trim();
        String gender = genderET.getText().toString().trim();

        accountInfo = new AccountInfo(name, number, age, bloodGroup, gender);

        updateAccountInfo();
    }

    private void updateAccountInfo() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("AccountInfo");

        userDB.setValue(accountInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), AccountInfoActivity.class));
                } else {
                    Toast.makeText(UpdateAccountInfoActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearField() {
        nameET.setText("");
        numberET.setText("");
        ageET.setText("");
        bloodGroupET.setText("");
        genderET.setText("");
    }

    private void downloadAccountInfo() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("AccountInfo");
        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    AccountInfo snapshotValue = dataSnapshot.getValue(AccountInfo.class);
                    setAccountInfoToField(snapshotValue);

                } else {
                    Toast.makeText(UpdateAccountInfoActivity.this, "Update User Info First", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setAccountInfoToField(AccountInfo accountInfo) {
        nameET.setText(accountInfo.getName());
        numberET.setText(accountInfo.getNumber());
        ageET.setText(String.valueOf(accountInfo.getAge()));
        bloodGroupET.setText(accountInfo.getBloodGroup());
        genderET.setText(accountInfo.getGender());
    }
}
