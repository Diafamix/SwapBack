package com.tfg.swapCatBack.core.controllers;

import com.tfg.swapCatBack.core.controllers.services.IHistoryService;
//import com.tfg.swapCatBack.core.services.IExcelService;
import com.tfg.swapCatBack.core.controllers.utils.RestResponse;
import com.tfg.swapCatBack.core.controllers.utils.TokenConsume;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/history")
@CrossOrigin("*")
@Tag(name = "History")
@Validated
public class HistoryController {

    private final IHistoryService historyService;
    //private final IExcelService excelService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get historical operations of the current user at a given date range")
    @TokenConsume(1)
    public RestResponse getHistoryByUserName(@DateTimeFormat(pattern = "dd-MM-yyyy") @NotNull LocalDate start,
                                             @DateTimeFormat(pattern = "dd-MM-yyyy") @NotNull LocalDate end) {
        return RestResponse.encapsulate(historyService.getAllRegisterUser(start, end));
    }

    @GetMapping("/download-to-excel")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get historical operations of the current user as a excel at a given date range (BETA)")
    @TokenConsume(2)
    public void downloadHistory(@DateTimeFormat(pattern = "dd-MM-yyyy") @NotNull LocalDate start,
                                @DateTimeFormat(pattern = "dd-MM-yyyy") @NotNull LocalDate end,
                                HttpServletResponse response) throws IOException {
        //excelService.downloadHistory(start, end, response);
    }
}
