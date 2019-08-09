package com.example.celebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCeleb extends AppCompatActivity {
    EditText etFirstName, etLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_celeb);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnInsert:
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();

                if (!(firstName.isEmpty()) || lastName.isEmpty()){
                    Celebs celebToInsert = new Celebs(firstName, lastName, false);
                    CelebDatabaseHelper dbHelper = new CelebDatabaseHelper(this);
                    dbHelper.insertCelebIntoDatabase(celebToInsert);

                    Toast.makeText(this, "Celeb Added", Toast.LENGTH_SHORT).show();



                }
                break;
            case R.id.btnGoToMain:
                startActivity(new Intent(this, MainActivity.class));

        }
    }
}
