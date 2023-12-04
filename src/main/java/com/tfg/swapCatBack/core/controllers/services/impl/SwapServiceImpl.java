package com.tfg.swapCatBack.core.controllers.services.impl;

import com.tfg.swapCatBack.core.controllers.services.IConvertorService;
import com.tfg.swapCatBack.core.controllers.services.ISwapService;
import com.tfg.swapCatBack.data.providers.IAccountProvider;
import com.tfg.swapCatBack.data.providers.IRegisterProvider;
import com.tfg.swapCatBack.data.providers.IUserProvider;
import com.tfg.swapCatBack.dto.data.response.SwapResponseDto;
import com.tfg.swapCatBack.dto.data.response.SwapUsersResponseDto;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
import com.tfg.swapCatBack.dto.integration.ConversorDTO;
import com.tfg.swapCatBack.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class SwapServiceImpl implements ISwapService {

    private final IUserProvider userProvider;
    private final IAccountProvider accountProvider;
    private final IConvertorService convertorService;
    private final SecurityContextHelper securityContextHelper;

    private final IRegisterProvider registerProvider;


    @Transactional
    @Override
    public SwapResponseDto swap(String from, String to, double amount) {
        UserResponseDTO user = securityContextHelper.getUser();
        ConversorDTO conversorDTO = convertorService.convert(from, to, amount).block();

        double toDeposit = conversorDTO.price * user.getType().getCommission();

        accountProvider.withDraw(user.username, from, amount);
        accountProvider.deposit(user.username, to, toDeposit);

        registerProvider.log(user.username, LocalDate.now(), from, to, amount);

        return SwapResponseDto.builder()
                .userName(user.username)
                .walletFrom(from)
                .walletTo(to)
                .amuountFrom(amount)
                .amountTo(toDeposit)
                .build();
    }

    @Override
    public SwapUsersResponseDto swap(String userTarget, String from, String to, double amount) {
        UserResponseDTO userFrom = securityContextHelper.getUser();
        ConversorDTO conversorDTO = convertorService.convert(from, to, amount).block();
        UserResponseDTO userTo = userProvider.getByName(userTarget);

        double toDeposit = conversorDTO.price * userTo.getType().getCommission();

        accountProvider.withDraw(userFrom.username, from, amount);
        accountProvider.deposit(userTo.username, to, toDeposit);

        registerProvider.log(userFrom.username, LocalDate.now(), from, to, amount);
        registerProvider.log(userTo.username, LocalDate.now(), to, from, toDeposit);

        return SwapUsersResponseDto.builder()
                .userFrom(userFrom.getUsername())
                .userTo(userTo.getUsername())
                .walletFrom(from)
                .walletTo(to)
                .amountFrom(amount)
                .amountTo(toDeposit)
                .build();
    }

}
