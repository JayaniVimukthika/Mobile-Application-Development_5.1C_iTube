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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnSignup,btnLogin;
    EditText username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        username =findViewById(R.id.username);
        password =findViewById(R.id.password);
        btnLogin =findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper db = new dbHelper(MainActivity.this);
                ArrayList<userModel> user = db.ValidLogin(username.getText().toString(),password.getText().toString());

                if (user.size() != 0) {
                    startActivity(new Intent(getApplicationContext(), homePage.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
                }




            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewItem = new Intent(getApplicationContext(), signUP.class);

                startActivity(viewItem);
            }
        });

    }
}