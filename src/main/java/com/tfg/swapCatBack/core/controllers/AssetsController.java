package com.tfg.swapCatBack.core.controllers;

import com.tfg.swapCatBack.core.controllers.services.IAssetsService;
import com.tfg.swapCatBack.core.controllers.utils.RestResponse;
import com.tfg.swapCatBack.core.controllers.utils.TokenConsume;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin("*")
@Tag(name = "Assets")
@Validated
public class AssetsController {

    private final IAssetsService assetsService;
    @GetMapping("assets")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all coins supported")
    public RestResponse list() {
        return RestResponse.encapsulate(assetsService.list());
    }

    @GetMapping("assets/{coinID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get metadata for an specific coin")
    @TokenConsume(2)
    public Mono<RestResponse> get(@PathVariable String coinID) {
        return assetsService.getMetadata(coinID)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("markets")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current data (name, price, market, ...) for all the coins supported")
    public Mono<RestResponse> getAll(@RequestParam(required = false) Optional<String> ids) {
        return ids
                .map(assetsService::getAll)
                .orElseGet(assetsService::getAll)
                .collectList()
                .map(RestResponse::encapsulate);
    }

    @GetMapping("markets/{coinID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current data (name, price, market, ...) for a coin by id")
    @TokenConsume(2)
    public Mono<RestResponse> getById(@PathVariable String coinID) {
        return assetsService.getById(coinID)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("assets/getHistory")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get historical data (name, price, market, stats) at a given date for a coin")
    @TokenConsume(2)
    public Mono<RestResponse> getHistory(@NotBlank String id,
                                         @NotBlank String vs_currency,
                                         @NotBlank String days,
                                         @RequestParam Optional<String> interval) {

        return assetsService.getAllHistory(id, vs_currency, days, interval)
                .collectList()
                .map(RestResponse::encapsulate);
    }

    @GetMapping("assets/getCandle")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get historical ohlc at a given date range for a coin")
    @TokenConsume(3)
    public Mono<RestResponse> getCandle(@NotBlank String id,
                                        @NotBlank String vs_currency,
                                        @NotBlank String days) {
        return assetsService.getAllCandles(id, vs_currency, days)
                .collectList()
                .map(RestResponse::encapsulate);
    }


}
