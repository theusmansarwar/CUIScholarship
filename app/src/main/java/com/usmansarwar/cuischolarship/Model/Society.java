package com.usmansarwar.cuischolarship.Model;

public class Society {
    private String title;
    private String description;
    private String PresidentName;
    private String PresidentRegNo;
    private String PresidentEmail;

    // Default constructor required for Firebase data deserialization
    public Society() {
        // Empty constructor
    }

    // Getter and setter methods for the society properties

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPresidentName() {
        return PresidentName;
    }

    public void setPresidentName(String PresidentName) {
        this.PresidentName = PresidentName;
    }

    public String getPresidentRegNo() {
        return PresidentRegNo;
    }

    public void setPresidentRegNo(String PresidentRegNo) {
        this.PresidentRegNo = PresidentRegNo;
    }

    public String getPresidentEmail() {
        return PresidentEmail;
    }

    public void setPresidentEmail(String PresidentEmail) {
        this.PresidentEmail = PresidentEmail;
    }
}
