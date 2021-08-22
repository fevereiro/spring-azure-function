package com.bankholidays.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bankholidays.models.EnglandWalesHolidays;
import com.bankholidays.models.Event;
import com.bankholidays.util.CsvGeneratorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankHolidayService {

    @Autowired
    private RestTemplate restTemplate;

    public File getCsvHolidaysByYear(String year) throws JsonProcessingException, FileNotFoundException {
        EnglandWalesHolidays englandWalesHolidays = this.fetchEnglandAndWalesHolidays();
        this.descEventsDateByYear(englandWalesHolidays, year);
        return CsvGeneratorUtil.writeToCSV(englandWalesHolidays);
    }

    private EnglandWalesHolidays fetchEnglandAndWalesHolidays() throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity("https://www.gov.uk/bank-holidays.json",
                String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("england-and-wales");
        return mapper.treeToValue(name, EnglandWalesHolidays.class);
    }

    private void descEventsDateByYear(EnglandWalesHolidays englandWalesHolidays, String year) {
        LocalDate initialDate = Year.parse(year).atMonth(Month.JANUARY).atDay(1);
        LocalDate endDate = Year.parse(year).atMonth(Month.DECEMBER).atDay(31);

        List<Event> dateEventsByYear = englandWalesHolidays.getEvents().stream().filter(event -> {
            LocalDate eventDate = LocalDate.parse(event.getDate());
            return eventDate.isAfter(initialDate) && eventDate.isBefore(endDate);
        }).sorted(Comparator.comparing(Event::getDate).reversed()).collect(Collectors.toList());

        englandWalesHolidays.setEvents(dateEventsByYear);
    }

}
