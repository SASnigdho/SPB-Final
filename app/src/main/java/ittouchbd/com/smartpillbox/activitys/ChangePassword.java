package ittouchbd.com.smartpillbox.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ittouchbd.com.smartpillbox.R;

public class ChangePassword extends AppCompatActivity {

    private EditText currentEmailET, currentPassET, newPassET;
    private Button requestBT;
    private TextView linkLogInTV;

    //Fire Base
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initializeComponent();
    }

    private void initializeComponent() {

        currentEmailET = findViewById(R.id.current_email_edt_Id);
        currentPassET = findViewById(R.id.current_password_edt_Id);
        newPassET = findViewById(R.id.new_Password_edt_Id);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.request_btn_Id) {
            validation();
        }

        else if (view.getId() == R.id.ui_link_login_btn_Id) {
            //Fire BaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        }

    }

    private void validation() {
        String currentEmail = currentEmailET.getText().toString().trim();
        String currPass = currentPassET.getText().toString().trim();
        final String newPass = newPassET.getText().toString().trim();

        AuthCredential credential = EmailAuthProvider.getCredential(currentEmail, currPass);
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePassword.this, "Password Update Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                            } else {
                                Toast.makeText(ChangePassword.this, "Password Not Update Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(ChangePassword.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
