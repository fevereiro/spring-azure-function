package com.bankholidays.models;

import java.util.List;

public class EnglandWalesHolidays {

    private List<Event> events;

    private String division;

    public String getDivision() {
        return this.division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
