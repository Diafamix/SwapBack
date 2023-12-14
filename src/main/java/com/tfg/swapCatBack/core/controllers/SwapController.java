package com.tfg.swapCatBack.core.controllers;

import com.tfg.swapCatBack.core.controllers.services.ISwapService;
import com.tfg.swapCatBack.core.controllers.utils.RestResponse;
import com.tfg.swapCatBack.core.controllers.utils.TokenConsume;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/trade")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Trade")
@Validated
public class SwapController {

    private final ISwapService swapService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Trades a certain amount of a coin to another in the portfolio of the current user")
    @TokenConsume(1)
    public RestResponse trade(@NotBlank String from,
                              @NotBlank String to,
                              @Positive Double amount) {
        return RestResponse.encapsulate(swapService.swap(from, to, amount));
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Send a certain amount of a coin from the current user to the given user")
    @TokenConsume(1)
    public RestResponse send(@NotBlank String userTo,
                             @NotBlank String from,
                             @NotBlank String to,
                             @Positive double amount) {
        return RestResponse.encapsulate(swapService.swap(userTo, from, to, amount));
    }


}
