package com.usmansarwar.cuischolarship.Model;

public class Scholarship {
    private String scholarshipTitle;
    private String eligibilityCriteria;
    private String scholarshipAmount;
    private String scholarshipType;

    // Default constructor required for calls to DataSnapshot.getValue(Scholarship.class)
    public Scholarship() {
    }

    public Scholarship(String scholarshipTitle, String eligibilityCriteria, String scholarshipAmount, String scholarshipType) {
        this.scholarshipTitle = scholarshipTitle;
        this.eligibilityCriteria = eligibilityCriteria;
        this.scholarshipAmount = scholarshipAmount;
        this.scholarshipType = scholarshipType;
    }

    public String getScholarshipTitle() {
        return scholarshipTitle;
    }

    public void setScholarshipTitle(String scholarshipTitle) {
        this.scholarshipTitle = scholarshipTitle;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public String getScholarshipAmount() {
        return scholarshipAmount;
    }

    public void setScholarshipAmount(String scholarshipAmount) {
        this.scholarshipAmount = scholarshipAmount;
    }

    public String getScholarshipType() {
        return scholarshipType;
    }

    public void setScholarshipType(String scholarshipType) {
        this.scholarshipType = scholarshipType;
    }
}
