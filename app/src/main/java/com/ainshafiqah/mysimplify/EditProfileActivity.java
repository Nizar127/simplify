package com.ainshafiqah.mysimplify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    EditText mEmail, mName, mNum;
    Button buttonSave;
    FirebaseAuth fAuth;
    DatabaseReference dbRef;
    String userID, userText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        String mmEmail = data.getStringExtra("Email");
        String mmName = data.getStringExtra("Name");
        String mmNum = data.getStringExtra("Phone Number");

        mEmail = findViewById(R.id.editPEmail);
        mName = findViewById(R.id.editPName);
        mNum = findViewById(R.id.editPNum);
        buttonSave = findViewById(R.id.btnSave);

        mEmail.setText(mmEmail);
        mName.setText(mmName);
        mNum.setText(mmNum);

        fAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        //userText = fAuth.getCurrentUser();
        userID = fAuth.getCurrentUser().getUid();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmail.getText().toString().isEmpty() || mName.getText().toString().isEmpty() || mNum.getText().toString().isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
                }

                //change email
                String email = mEmail.getText().toString();
                String name = mName.getText().toString();
                String num = mNum.getText().toString();
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("email",email);
                userMap.put("name",name);
                userMap.put("phoneNum",num);
                dbRef.child(userID).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditProfileActivity.this, MoreActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(EditProfileActivity.this, "Email has been successfully changed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}