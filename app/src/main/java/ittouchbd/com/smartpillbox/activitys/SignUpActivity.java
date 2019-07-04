package ittouchbd.com.smartpillbox.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import ittouchbd.com.smartpillbox.MainActivity;
import ittouchbd.com.smartpillbox.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailET, passET, reEnterPasswordET;
    private Button signUpBT;
    private TextView logInTV;
    private ProgressBar progressBar;

    //Fire Base
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeComponent();
    }

    private void initializeComponent() {
        emailET = findViewById(R.id.signUp_email_edt_Id);
        passET = findViewById(R.id.signUp_password_edt_Id);
        reEnterPasswordET = findViewById(R.id.signUp_reEnterPassword_edt_Id);

        signUpBT = findViewById(R.id.signUp_btn_Id);
        logInTV = findViewById(R.id.link_login);
        progressBar = findViewById(R.id.signUp_pb_Id);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View view) {

        if (view.getId() == R.id.signUp_btn_Id) {
            checkValidity();
        }
        else if (view.getId() == R.id.link_login) {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        }
    }

    private void checkValidity() {
        String email = emailET.getText().toString().trim();
        String pass = passET.getText().toString().trim();
        String rePass = reEnterPasswordET.getText().toString().trim();

        if(email.isEmpty())
        {
            emailET.setError("Enter an email address");
            emailET.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailET.setError("Enter a valid email address");
            emailET.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            emailET.setError("Enter a password");
            emailET.requestFocus();
            return;
        }

        if (!pass.equals(rePass)) {
            passET.setError("Enter a password");
            passET.requestFocus();
            reEnterPasswordET.setError("Enter a password");
            reEnterPasswordET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        createAccount(email, pass);
    }

    private void createAccount(String email, String pass) {

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Account is Created Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));

                    clearAllInputField();
                }
                else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(SignUpActivity.this, "Already Have a Account!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void clearAllInputField() {
        emailET.setText("");
        passET.setText("");
        reEnterPasswordET.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
