package com.example.eggdelivery.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.eggdelivery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    static final int IMG_PICKED_CODE = 774;


    ImageView img;
    Button btn;
    Uri image;

    private EditText semail, sname, spassword, srepassword;
    private String email, name, password, repassword;
    private ProgressBar progressBar;
    private ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inits();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

    }

    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select DP"), IMG_PICKED_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMG_PICKED_CODE && resultCode == RESULT_OK && data.getData() != null) {
            image = data.getData();
            img.setImageURI(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void inits() {
        semail = findViewById(R.id.logem);
        sname = findViewById(R.id.signname);
        spassword = findViewById(R.id.logpass);
        srepassword = findViewById(R.id.repass);
        progressBar = findViewById(R.id.progressBar2);
        btn = findViewById(R.id.btnimg);
        img = findViewById(R.id.imageView2);

    }


    private boolean validateFields() {
        String email = semail.getText().toString().trim();
        String name = sname.getText().toString().trim();
        String password = spassword.getText().toString().trim();
        String repassword = srepassword.getText().toString().trim();

        if (email.isEmpty()) {
            semail.setError("Email Cant be empty");
            semail.requestFocus();
            return false;
        } else if (name.isEmpty()) {
            sname.setError("Name Field Cant be empty");
            sname.requestFocus();
            return false;
        } else if (password.isEmpty()) {
            spassword.setError("Password Field Cant be empty");
            spassword.requestFocus();
            return false;
        } else if (repassword.isEmpty()) {
            srepassword.setError("RePassword Field Cant be empty");
            srepassword.requestFocus();
            return false;
        } else if (!password.equals(repassword)) {
            srepassword.setError("Passwords Dont Match");
            srepassword.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            semail.setError("Email is invalid");
            semail.requestFocus();
            return false;
        }
        return true;
    }

    public void Account(View view) {
        startActivity(new Intent(Signup.this, Login.class));
    }

    public void signup(View view) {

        if (!validateFields()) {
            return;
        }

        String email = semail.getText().toString().trim();
        String name = sname.getText().toString().trim();
        String password = spassword.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        startActivity(new Intent(Signup.this, Login.class));

    }
}