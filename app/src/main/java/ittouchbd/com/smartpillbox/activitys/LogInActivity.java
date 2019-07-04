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

import ittouchbd.com.smartpillbox.MainActivity;
import ittouchbd.com.smartpillbox.R;

public class LogInActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private Button logInBT;
    private TextView signUpTV;
    private ProgressBar logInProgressBar;

    //Fire Base
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initializeComponent();
    }

    private void initializeComponent() {
        emailET = findViewById(R.id.logIn_email_Id);
        passwordET = findViewById(R.id.login_password_Id);
        logInBT = findViewById(R.id.btn_login);
        signUpTV = findViewById(R.id.link_signup);
        logInProgressBar = findViewById(R.id.logIn_pb_Id);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View view) {

        if (view.getId() == R.id.btn_login) {
            checkValidityForLogIn();
        }

        else if (view.getId() == R.id.link_signup) {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        }
    }

    private void checkValidityForLogIn() {
        String email = emailET.getText().toString().trim();
        String pass = passwordET.getText().toString().trim();

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
        if(pass.isEmpty())
        {
            emailET.setError("Enter a password");
            emailET.requestFocus();
            return;
        }

        logInProgressBar.setVisibility(View.VISIBLE);
        logInWithEmailAndPassWord(email, pass);
    }

    private void logInWithEmailAndPassWord(String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                logInProgressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(LogInActivity.this, "Faild!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
