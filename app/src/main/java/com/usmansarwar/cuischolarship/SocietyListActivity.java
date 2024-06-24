package com.usmansarwar.cuischolarship;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usmansarwar.cuischolarship.Model.Society;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SocietyListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SocietyAdapter societyAdapter;
    private List<Society> societyList = new ArrayList<>();
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_list);

        // Initialize the RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recyclerViewSociety);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a new adapter with an empty list
        societyAdapter = new SocietyAdapter(societyList);
        recyclerView.setAdapter(societyAdapter);

        // Get the reference to the "Societies" node in the Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference("Societies");

        // Fetch societies data from Firebase Realtime Database
        fetchSocietiesData();
    }

    private void fetchSocietiesData() {
        // Add a value event listener to the database reference to fetch data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                societyList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Society society = snapshot.getValue(Society.class);
                    if (society != null) {
                        societyList.add(society);
                    }
                }
                societyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("SocietyListActivity", "Failed to read data from the database", databaseError.toException());
                Toast.makeText(SocietyListActivity.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
