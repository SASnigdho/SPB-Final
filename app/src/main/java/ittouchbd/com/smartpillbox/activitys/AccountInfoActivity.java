package ittouchbd.com.smartpillbox.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.model.AccountInfo;

public class AccountInfoActivity extends AppCompatActivity {

    private TextView nameTV, numberTV, ageTV, bloodGroupTV, gender, emailTV;

    //Firebase
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        initializeView();
    }

    private void initializeView() {
        firebaseDatabase = FirebaseDatabase.getInstance();

        nameTV = findViewById(R.id.ai_name_tv_Id);
        numberTV = findViewById(R.id.ai_mobileNumber_tv_Id);
        ageTV = findViewById(R.id.ai_age_tv_Id);
        bloodGroupTV = findViewById(R.id.ai_bloodGrou_tv_Id);
        gender = findViewById(R.id.ai_gender_tv_Id);
        emailTV = findViewById(R.id.ai_email_tv_Id);

        findViewById(R.id.ai_update_btn_Id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UpdateAccountInfoActivity.class));
            }
        });

        findViewById(R.id.ai_changePassword_btn_Id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAccountInfo();
    }

    private void loadAccountInfo() {
        DatabaseReference userDB = firebaseDatabase.getReference().child("appUser").child("AccountInfo");

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    AccountInfo value = dataSnapshot.getValue(AccountInfo.class);
                    showValue(value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showValue(AccountInfo value) {
        nameTV.setText("Name: " + value.getName());
        numberTV.setText("Number: " + value.getNumber());
        ageTV.setText("Age: " + value.getAge());
        bloodGroupTV.setText("Blood Group: " + value.getBloodGroup());
        gender.setText("Gender: " + value.getGender());
    }
}
