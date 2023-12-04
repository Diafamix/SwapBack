package com.tfg.swapCatBack.core.controllers;

import com.tfg.swapCatBack.core.controllers.services.IAutentificationService;
import com.tfg.swapCatBack.core.controllers.utils.RestResponse;
import com.tfg.swapCatBack.data.entities.enums.UserRole;
import com.tfg.swapCatBack.data.entities.enums.UserType;
import com.tfg.swapCatBack.dto.data.request.UserRegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/autentication")
@CrossOrigin("*")
@Tag(name = "Authentication")
public class AutentificationController {

    private final IAutentificationService authenticationService;

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check if a user can be logged with that credentials")
    public RestResponse login(String username, String password) {
        return RestResponse.encapsulate(Pair.of("result", authenticationService.login(username, password)));
    }

    @GetMapping("/loginv2")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check if a user can be logged with that credentials")
    public RestResponse loginv2(String mail, String password) {
        return RestResponse.encapsulate(Pair.of("result", authenticationService.loginv2(mail, password)));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registers a new user")
    public RestResponse register(String mail, String username, String password) {
        return RestResponse.encapsulate(authenticationService.register(
                UserRegisterDTO.builder()
                        .username(username)
                        .mail(mail)
                        .password(password)
                        .role(UserRole.USER)
                        .type(UserType.FREE)
                        .build()

        ));
    }

}
