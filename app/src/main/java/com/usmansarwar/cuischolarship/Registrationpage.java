package com.usmansarwar.cuischolarship;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.usmansarwar.cuischolarship.Model.USERS;
import com.usmansarwar.cuischolarship.Model.USERS;
import com.usmansarwar.cuischolarship.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Registrationpage extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextStudentID, editTextPhone, editTextPassword;
    private Spinner spinnerDepartment;
private TextView Backtologin;
    private Button buttonRegister;

    // Firebase instances
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextStudentID = findViewById(R.id.editTextStudentID);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        spinnerDepartment = findViewById(R.id.autoCompleteDepartment);
        buttonRegister = findViewById(R.id.buttonRegister);
        Backtologin= findViewById(R.id.textViewBackToLogin);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("userdata");

        // Set up the dropdown list for the department
        setupDepartmentDropdown();

        // Set click listener for the register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                Intent intent = new Intent(Registrationpage.this, Login.class);
                startActivity(intent);
            }
        });
        Backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrationpage.this, Login.class);
                startActivity(intent);
            }
        });

    }

    private void setupDepartmentDropdown() {
        List<String> departmentList = new ArrayList<>();
        departmentList.add("Computer Science");
        departmentList.add("Electrical Engineering");
        departmentList.add("Business Administration");
        departmentList.add("Mechanical Engineering");

        // Create an ArrayAdapter for the Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                departmentList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the Spinner
        spinnerDepartment.setAdapter(adapter);
    }

    private void registerUser() {
        // Retrieve input data
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String studentID = editTextStudentID.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String department = spinnerDepartment.getSelectedItem().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate input data
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(studentID) ||
                TextUtils.isEmpty(phone) || TextUtils.isEmpty(department) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register the user with Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // User registration successful
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveUserData(user.getUid(), name, email, studentID, phone, department, password);

                        }
                    } else {
                        // User registration failed
                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserData(String uid, String name, String email, String studentID, String phone, String department, String password) {
        // Create a USER object and set the default values for president, societyId, and type
        USERS user = new USERS();
        user.setName(name);
        user.setEmail(email);
        user.setRegistrationNo(studentID);
        user.setPhoneNumber(phone);
        user.setProgram(department);
        user.setPassword(password);

        // Set default values
        user.setType("user");
        user.setPresident("no");
        user.setSocietyId("none");

        // Save the user object to the database
        databaseReference.child(uid).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "User registered and data saved", Toast.LENGTH_SHORT).show();
                        // Navigate to login page or close activity
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to save user data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
