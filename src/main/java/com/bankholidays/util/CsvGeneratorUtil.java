package com.bankholidays.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bankholidays.models.EnglandWalesHolidays;

public final class CsvGeneratorUtil {

    private static final String FILE_PREFIX = "england-wales-bank-holiday";
    private static final String HEADER_FIELDS = "Title;Date;Notes;isBunting";

    private CsvGeneratorUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static File writeToCSV(EnglandWalesHolidays englandWalesHolidays) throws FileNotFoundException {
        File csvOutputFile = new File(FILE_PREFIX + ".csv");

        try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
            printWriter.println(HEADER_FIELDS);
            englandWalesHolidays.getEvents().stream().forEach(event -> {
                String convertedData = convertToCSV(new String[] { event.getTitle(), event.getDate(), event.getNotes(),
                        event.isBunting() ? "Yes" : "No" });
                printWriter.println(convertedData);
            });
        }

        return csvOutputFile;
    }

    private static String convertToCSV(String[] data) {
        return Stream.of(data).map(CsvGeneratorUtil::escapeSpecialCharacters).collect(Collectors.joining(";"));
    }

    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
