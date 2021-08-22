package com.bankholidays.models;

public class Event {

    private String title;

    private String date;

    private String notes;

    private boolean bunting;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isBunting() {
        return this.bunting;
    }

    public boolean getBunting() {
        return this.bunting;
    }

    public void setBunting(boolean bunting) {
        this.bunting = bunting;
    }
}