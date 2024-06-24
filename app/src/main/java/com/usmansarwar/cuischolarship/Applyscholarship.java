package com.usmansarwar.cuischolarship;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.usmansarwar.cuischolarship.Model.USERS;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.HashMap;
import java.util.Map;

public class Applyscholarship extends AppCompatActivity {

    // Request code for file selection
    private static final int REQUEST_CODE_SELECT_FILE = 1;

    // User data variables
    private String registrationNo;
    private String name;
    private String email;
    private String phoneNumber;

    // UI elements
    private TextView titleTextView;
    private TextView fileChosenTextView;
    private TextView scholarshipTitleTextView;
    private TextView Backbtn;
    private TextView emailTextView;
    private TextView phoneTextView;
    private TextView regNoTextView;
    private Button chooseFileButton;
    private Button submitButton;

    // File URL
    private String fileURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyscholarship);

        // Initialize UI elements
        titleTextView = findViewById(R.id.textView13);
        fileChosenTextView = findViewById(R.id.textView22);
        scholarshipTitleTextView = findViewById(R.id.textView20);

        chooseFileButton = findViewById(R.id.button);
        submitButton = findViewById(R.id.button2);
        Backbtn=findViewById(R.id.textView23);
        // Get scholarship title from intent
        Intent intent = getIntent();
        String scholarshipTitle = intent.getStringExtra("scholarshipName");
        scholarshipTitleTextView.setText(scholarshipTitle);

        // Retrieve user data from Firebase Realtime Database
        retrieveUserData();

        // Set up the "Choose File" button
        chooseFileButton.setOnClickListener(v -> chooseFile());

        // Set up the "Submit" button
        submitButton.setOnClickListener(v -> submitApplication(scholarshipTitle));
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Applyscholarship.this, Scholarships.class);
                startActivity(intent);
            }
        });
    }

    // Method to retrieve user data from Firebase Realtime Database
    private void retrieveUserData() {
        // Get Firebase Database reference
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        // Get the current user's ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Reference to the user's data
        DatabaseReference userRef = databaseRef.child("userdata").child(userId);

        // Attach a listener to retrieve user data
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    USERS user = dataSnapshot.getValue(USERS.class);
                    if (user != null) {
                        // Store user's email, registration ID, name, and phone number
                        email = user.getEmail();
                        registrationNo = user.getRegistrationNo();
                        name = user.getName();
                        phoneNumber = user.getPhoneNumber();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to retrieve user data: " + databaseError.getMessage());
                Toast.makeText(Applyscholarship.this, "Failed to retrieve user data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to choose a file
    private void chooseFile() {
        // Create an intent to open the file selection
        Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        fileIntent.setType("*/*"); // Allow the user to select any file type
        startActivityForResult(fileIntent, REQUEST_CODE_SELECT_FILE);
    }

    // Handle the file selection result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_FILE && resultCode == RESULT_OK && data != null) {
            // Get the selected file URI
            Uri selectedFileUri = data.getData();

            if (selectedFileUri != null) {
                // Upload the file and get the file URL
                uploadFile(selectedFileUri);
            }
        }
    }

    // Method to upload file and get the file URL
    private void uploadFile(Uri fileUri) {
        // Create a storage reference
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        // Create a reference for the file in Firebase Storage
        StorageReference fileRef = storageRef.child("ScholarshipsData").child(registrationNo + ".pdf");

        // Upload the file
        UploadTask uploadTask = fileRef.putFile(fileUri);

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Retrieve the file URL once the upload is successful
            fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                fileURL = uri.toString();
                fileChosenTextView.setText("File chosen: " + fileURL);
            });
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Toast.makeText(this, "Failed to upload file: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    // Method to submit application data to Firebase Realtime Database
    private void submitApplication(String scholarshipTitle) {
        if (email == null || fileURL == null || name == null || phoneNumber == null || registrationNo == null) {
            Toast.makeText(this, "Please wait while your data is being loaded.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare the application data
        Map<String, Object> applicationData = new HashMap<>();
        applicationData.put("email", email);
        applicationData.put("fileURL", fileURL);
        applicationData.put("name", name);
        applicationData.put("phoneNumber", phoneNumber);
        applicationData.put("registrationNo", registrationNo);
        applicationData.put("status", "pending");
        applicationData.put("title", scholarshipTitle);

        // Reference to Firebase Database
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // Push the application data to the "applications" node using push() for a unique key
        database.child("applications").push().setValue(applicationData)
                .addOnSuccessListener(aVoid -> {
                    // Handle successful data saving
                    Log.d("FirebaseDatabase", "Application data saved successfully.");
                    Toast.makeText(this, "Application submitted successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle errors
                    Log.e("FirebaseDatabase", "Failed to save application data: " + e.getMessage());
                    Toast.makeText(this, "Failed to submit application: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
