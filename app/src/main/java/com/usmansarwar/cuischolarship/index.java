package com.usmansarwar.cuischolarship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class index extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        CardView Studentcard = findViewById(R.id.studentcard);
        Studentcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the card view is clicked
                Intent intent = new Intent(index.this, Login.class);
                startActivity(intent);
            }
        });




    }}