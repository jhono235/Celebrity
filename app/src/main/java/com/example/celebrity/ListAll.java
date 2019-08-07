package com.example.celebrity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListAll extends AppCompatActivity {

    RecyclerView rvCelebList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        rvCelebList = findViewById(R.id.recyclerView);
        CelebDatabaseHelper celebDatabaseHelper = new CelebDatabaseHelper(this);

        CelebRecyclerViewAdapter celebRecyclerViewAdapter = new CelebRecyclerViewAdapter(celebDatabaseHelper.getAllCelebs());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCelebList.setLayoutManager(layoutManager);
        rvCelebList.setAdapter(celebRecyclerViewAdapter);




    }
}
