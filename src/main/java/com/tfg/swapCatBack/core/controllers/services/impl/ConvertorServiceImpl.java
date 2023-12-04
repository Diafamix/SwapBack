package com.tfg.swapCatBack.core.controllers.services.impl;

import com.tfg.swapCatBack.core.controllers.services.IConvertorService;
import com.tfg.swapCatBack.data.providers.ICoinProvider;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.dto.integration.ConversorDTO;
import com.tfg.swapCatBack.integration.services.ICoinIntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ConvertorServiceImpl implements IConvertorService {

    private final ICoinIntegrationService coinService;
    private final ICoinProvider coinProvider;

    @Override
    public Mono<ConversorDTO> convert(String from, double amount) {
        CoinResponseDTO fromCoin = coinProvider.getCoinById(from);

        return coinService.convert(fromCoin.symbol, amount);
    }

    @Override
    public Mono<ConversorDTO> convert(String from, String to, double amount) {
        CoinResponseDTO fromCoin = coinProvider.getCoinById(from);
        CoinResponseDTO toCoin = coinProvider.getCoinById(to);

        return coinService.convert(fromCoin.symbol, toCoin.symbol, amount);
    }

}
