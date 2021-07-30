package com.ainshafiqah.mysimplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    String inputEmail, inputPassword;
    EditText email, password;
    TextView regButton;
    Button buttonLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        regButton = findViewById(R.id.signup);
        //signUP = findViewById(R.id.signup);

        fAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEmail =  email.getText().toString().trim();
                inputPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(inputEmail)) {
                    Toast.makeText(LoginActivity.this, "Email is required", Toast.LENGTH_SHORT).show();

                }

                if (TextUtils.isEmpty(inputPassword)) {
                    Toast.makeText(LoginActivity.this, "Phone is required", Toast.LENGTH_SHORT).show();

                }
                fAuth.signInWithEmailAndPassword(inputEmail,inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Phone is required", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}