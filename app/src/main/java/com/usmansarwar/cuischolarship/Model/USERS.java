package com.usmansarwar.cuischolarship.Model;

public class USERS {

    // Fields corresponding to the provided database structure
    private String name;
    private String password;
    private String phoneNumber;
    private String president;
    private String program;
    private String registrationNo;
    private String societyId;
    private String type;
    private  String email;

    public String getEmail() {
        return email;
    }


    // Constructor
    public USERS() {
        // Default constructor
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for president
    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    // Getter and Setter for program
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    // Getter and Setter for registrationNo
    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    // Getter and Setter for societyId
    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email= email;

    }
}
