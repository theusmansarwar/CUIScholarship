package com.usmansarwar.cuischolarship;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usmansarwar.cuischolarship.Model.Scholarship;
import com.usmansarwar.cuischolarship.R;
import com.usmansarwar.cuischolarship.ScholarshipAdapter;
import com.usmansarwar.cuischolarship.page1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Scholarships extends AppCompatActivity {

    // Existing variables
    private RecyclerView recyclerView;
    private ScholarshipAdapter scholarshipAdapter;
    private List<Scholarship> scholarshipList = new ArrayList<>();
    private DatabaseReference databaseReference;
    private ImageView Backbtn;

    // Search bar
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarships);

        // Existing code
        recyclerView = findViewById(R.id.recycler_view);
        Backbtn = findViewById(R.id.imageView13);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scholarshipAdapter = new ScholarshipAdapter(scholarshipList);
        recyclerView.setAdapter(scholarshipAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Scholarshipdata");
        fetchScholarshipData();

        Backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Scholarships.this, page1.class);
            startActivity(intent);
        });

        // Initialize the search bar
        searchBar = findViewById(R.id.editTextText7);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No action needed here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Filter scholarships based on input
                filterScholarships(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed here
            }
        });
    }

    private void fetchScholarshipData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scholarshipList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Scholarship scholarship = snapshot.getValue(Scholarship.class);
                    if (scholarship != null) {
                        scholarshipList.add(scholarship);
                    }
                }
                scholarshipAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Scholarships", "Failed to read data from the database", databaseError.toException());
            }
        });
    }

    // Method to filter scholarships based on the title input in the search bar
    private void filterScholarships(String query) {
        List<Scholarship> filteredScholarships = new ArrayList<>();

        // Iterate through the list of scholarships and add to filtered list if the title contains the query
        for (Scholarship scholarship : scholarshipList) {
            if (scholarship.getScholarshipTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredScholarships.add(scholarship);
            }
        }

        // Update the adapter with the filtered list
        scholarshipAdapter.updateScholarshipList(filteredScholarships);
    }
}
