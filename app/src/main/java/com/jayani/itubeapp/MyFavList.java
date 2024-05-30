package com.jayani.itubeapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyFavList extends AppCompatActivity {

    private RecyclerView itemRecyclerView;
    private itemAdapter itemAdapters;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_fav_list);

        dbHelper databaseHelperClass = new dbHelper(this);

        ArrayList<itemModel> itemsLists = databaseHelperClass.getItemList();

        itemRecyclerView = findViewById(R.id.itemRecycler);
        itemRecyclerView.setHasFixedSize(true);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapters =new itemAdapter(itemsLists);
        itemRecyclerView.setAdapter(itemAdapters);

        itemAdapters.setOnClickListner(new itemAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                int itemId =itemsLists.get(position).getId();
                String  url =itemsLists.get(position).getUrl();


                Intent viewItem = new Intent(getApplicationContext(), youtubePlay.class);

                viewItem.putExtra("url", String.valueOf(url));

                startActivity(viewItem);
            }
        });

    }
}