package com.tfg.swapCatBack.core.controllers;

import com.tfg.swapCatBack.core.controllers.services.IAssetsService;
import com.tfg.swapCatBack.core.controllers.utils.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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

}
