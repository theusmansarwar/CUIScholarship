package com.usmansarwar.cuischolarship;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usmansarwar.cuischolarship.Model.Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activities extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActivityAdapter activityAdapter;
    private List<Activity> activityList = new ArrayList<>();
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.listofactivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with the list of activities
        activityAdapter = new ActivityAdapter(activityList, this);
        recyclerView.setAdapter(activityAdapter);

        // Get reference to the "Activities" node in Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Activities");

        // Fetch activities data from Firebase
        fetchActivitiesData();
    }

    private void fetchActivitiesData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear the existing list of activities
                activityList.clear();

                // Iterate through the data snapshot and parse the Activity objects
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Activity activity = snapshot.getValue(Activity.class);
                    if (activity != null) {
                        activityList.add(activity);
                    }
                }

                // Notify the adapter that data has changed
                activityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Activities", "Failed to read data from Firebase: " + databaseError.getMessage());
                Toast.makeText(Activities.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
