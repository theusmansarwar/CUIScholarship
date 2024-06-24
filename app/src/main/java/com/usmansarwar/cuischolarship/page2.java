package com.usmansarwar.cuischolarship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        TextView Textview3 = findViewById(R.id.textView34);
        Textview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the card view is clicked
                Intent intent = new Intent(page2.this,Login.class);
                startActivity(intent);
            }
        });
    }
}