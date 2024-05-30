package com.jayani.itubeapp;

import android.content.Intent;
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

public class homePage extends AppCompatActivity {

    EditText linkUrl;
    Button btnPlay,btnAddPlayList,btnMyPlayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);



        linkUrl = findViewById(R.id.linkUrl);
        btnPlay = findViewById(R.id.btnPlay);
        btnAddPlayList = findViewById(R.id.btnAddPlayList);
        btnMyPlayList = findViewById(R.id.btnMyPlayList);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = linkUrl.getText().toString();
                Intent viewItem = new Intent(getApplicationContext(), youtubePlay.class);
                viewItem.putExtra("url", url);
                startActivity(viewItem);
            }
        });

        btnAddPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper db = new dbHelper(homePage.this);
                itemModel itemModel = new itemModel(linkUrl.getText().toString());
                db.addItem(itemModel);
//                itemAdapter.notifyChange();
                Toast.makeText(getApplicationContext(), "Add my Fav Successfully", Toast.LENGTH_SHORT).show();
                linkUrl.setText("");
//                finish();
            }
        });

        btnMyPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewItem = new Intent(getApplicationContext(), MyFavList.class);
                startActivity(viewItem);
            }
        });

    }
}