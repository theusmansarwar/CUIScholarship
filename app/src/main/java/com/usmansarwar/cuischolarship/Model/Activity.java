package com.usmansarwar.cuischolarship.Model;

public class Activity {
    private String title;
    private String description;
    private String EventDate;
    private String EventVenue;
    private String SocietyName;
    private String applyLink;
    private String uploadDate;

    // Constructor
    public Activity() {
        // Required empty constructor for Firebase
    }

    // Additional constructor for convenience (optional)
    public Activity(String title, String description, String EventDate, String EventVenue, String SocietyName, String applyLink, String uploadDate) {
        this.title = title;
        this.description = description;
        this.EventDate = EventDate;
        this.EventVenue = EventVenue;
        this.SocietyName = SocietyName;
        this.applyLink = applyLink;
        this.uploadDate = uploadDate;
    }

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

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        this.EventDate = eventDate;
    }

    public String getEventVenue() {
        return EventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.EventVenue = eventVenue;
    }

    public String getSocietyName() {
        return SocietyName;
    }

    public void setSocietyName(String societyName) {
        this.SocietyName = societyName;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
