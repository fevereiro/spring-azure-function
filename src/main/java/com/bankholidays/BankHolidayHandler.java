package com.bankholidays;

import java.util.Optional;

import com.bankholidays.dto.ResponseDTO;
import com.bankholidays.services.BankHolidayService;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

public class BankHolidayHandler extends FunctionInvoker<String, ResponseDTO> {

        @Autowired
        BankHolidayService bankHolidayService;

        @FunctionName("englandWalesBankHolidays")
        public HttpResponseMessage execute(@HttpTrigger(name = "request", methods = { HttpMethod.GET,
                        HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
                        ExecutionContext context) {

                String year = request.getQueryParameters().getOrDefault("year", "2021");
                ResponseDTO response = handleRequest(year, context);

                if (response.hasError()) {
                        return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(response.getErrorMessage()).build();
                }

                return request.createResponseBuilder(HttpStatus.OK).body(response.getFileOutput())
                                .header("Content-Disposition",
                                                "attachment; filename=england-wales-bank-holidays" + year + ".csv")
                                .header("Content-Type", "text/csv").build();

        }
}
