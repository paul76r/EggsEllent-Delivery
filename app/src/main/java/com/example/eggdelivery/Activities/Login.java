package com.example.eggdelivery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eggdelivery.R;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText etemail, etpassword;
    private String semail, spass;
    private Button btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private TextView frgtpass, signup;
    private ImageView backbtn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String shared_db = "logindata";
    private static final String email_key = "email";
    private static final String pass_key = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inits();

        String email = sharedPreferences.getString(email_key, "");
        String password = sharedPreferences.getString(pass_key, "");

        etemail.setText(email);
        etpassword.setText(password);

        btn.setOnClickListener(this);
        frgtpass.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    public void inits() {
        etemail = findViewById(R.id.uemail);
        etpassword = findViewById(R.id.upass);
        btn = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressB);
        frgtpass = findViewById(R.id.fgtps);
        signup = findViewById(R.id.sgnup);
        sharedPreferences = getSharedPreferences(shared_db, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                userLogin();
                break;
            case R.id.sgnup:
                startActivity(new Intent(Login.this, Signup.class));
                break;
        }
    }

    private void userLogin() {
        String semail = etemail.getText().toString().trim();
        String spass = etpassword.getText().toString().trim();

        if (semail.isEmpty()) {
            etemail.setError("Email is required!");
            etemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(semail).matches()) {
            etemail.setError("Please Enter a valid Email!");
            etemail.requestFocus();
            return;
        }

        if (spass.isEmpty()) {
            etpassword.setError("Password is required!");
            etpassword.requestFocus();
            return;
        }

        if (spass.length() < 6) {
            etpassword.setError("Min password is 6 characters!");
            etpassword.requestFocus();
            return;
        }

        editor.putString(email_key, semail);
        editor.putString(pass_key, spass);
        editor.apply();

        progressBar.setVisibility(View.VISIBLE);

        startActivity(new Intent(Login.this, MainActivity.class));
    }
}