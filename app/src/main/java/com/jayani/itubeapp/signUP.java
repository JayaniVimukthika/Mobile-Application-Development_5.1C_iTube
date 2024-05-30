package com.jayani.itubeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signUP extends AppCompatActivity {

    EditText fName,username,password,confirmPass;
    Button btnSignUP;
    String user ="";
    String pass ="";


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        fName = findViewById(R.id.fName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPass);
        btnSignUP = findViewById(R.id.btnSignUP);

        sharedPreferences = getSharedPreferences("iTubePrefs", MODE_PRIVATE);

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(fName.getText().toString().isEmpty()||
                        username.getText().toString().isEmpty()||
                        password.getText().toString().isEmpty() || confirmPass.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "Fields can't be blank",Toast.LENGTH_LONG).show();
                }

                else if (!password.getText().toString().equals(confirmPass.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),
                            "Password and confirm password not matched",Toast.LENGTH_LONG).show();
                }
                else {
                    dbHelper db = new dbHelper(signUP.this);
                    userModel user = new userModel(username.getText().toString(),password.getText().toString());
                    db.userAdd(user);
                    Toast.makeText(getApplicationContext(), "Create user Successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }
}