package com.usmansarwar.cuischolarship;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usmansarwar.cuischolarship.Model.Application;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Status extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApplicationAdapter applicationAdapter;
    private List<Application> applicationList = new ArrayList<>();
    private DatabaseReference databaseReference;
    private ImageView Backbtn;
    private EditText searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        // Initialize the RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.listofapplication);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a new adapter with an empty list
        applicationAdapter = new ApplicationAdapter(applicationList);
        recyclerView.setAdapter(applicationAdapter);

        // Get the reference to the "applications" node in the Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference("applications");

        // Fetch the user's applications data from Firebase Realtime Database
        fetchUserApplications();

        // Add custom item decoration for spacing
        int spacing = 8; // Set desired spacing in dp
        float density = getResources().getDisplayMetrics().density;
        int spacingInPixels = (int) (spacing * density + 0.5f);
        CustomItemDecoration itemDecoration = new CustomItemDecoration(spacingInPixels);
        recyclerView.addItemDecoration(itemDecoration);
        Backbtn = findViewById(R.id.imageView13);
        Backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Status.this, page1.class);
            startActivity(intent);
        });
    }

    private void fetchUserApplications() {
        // Get the current user email
        String currentUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // Add a value event listener to the database reference to fetch data
        databaseReference.orderByChild("email").equalTo(currentUserEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                applicationList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Application application = snapshot.getValue(Application.class);
                    if (application != null) {
                        applicationList.add(application);
                    }
                }
                applicationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("StatusActivity", "Failed to read data from the database", databaseError.toException());
                Toast.makeText(Status.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
