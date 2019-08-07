package com.example.celebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddCeleb:
                startActivity(new Intent(this, AddCeleb.class));
            break;
            case R.id.btnViewAllCelebs:
                startActivity(new Intent(this, ListAll.class));
            break;
        }
    }
}
