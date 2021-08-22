package com.bankholidays.functions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;

import com.bankholidays.dto.ResponseDTO;
import com.bankholidays.services.BankHolidayService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class BankHolidayFunction implements Function<Mono<String>, Mono<ResponseDTO>> {

    @Autowired
    BankHolidayService bankHolidayService;

    public Mono<ResponseDTO> apply(Mono<String> mono) {
        return mono.map(year -> {
            ResponseDTO response = new ResponseDTO();

            try {
                response.setFileOutput(Files.readAllBytes(bankHolidayService.getCsvHolidaysByYear(year).toPath()));
            } catch (JsonProcessingException e) {
                fillResponseError(response, e, "Error trying to fetch bank holidays");
            } catch (IOException e) {
                fillResponseError(response, e, "Error on generating .csv file");
            }

            return response;
        });
    }

    private void fillResponseError(ResponseDTO response, Exception e, String message) {
        response.setHasError(true);
        response.setErrorMessage(message);
        e.printStackTrace();
    }
}
