package com.tfg.swapCatBack.core.controllers.services.impl;

import com.tfg.swapCatBack.core.controllers.services.IAssetsService;
import com.tfg.swapCatBack.data.providers.ICoinProvider;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.controller.CoinDTO;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.dto.integration.CandleInfoDTO;
import com.tfg.swapCatBack.dto.integration.CoinMetadataDTO;
import com.tfg.swapCatBack.dto.integration.HistoryInfoDTO;
import com.tfg.swapCatBack.integration.services.ICoinIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class AssetsServiceImpl implements IAssetsService {

    @Autowired
    private ICoinProvider coinProvider;

    @Autowired
    @Qualifier("Cache")
    private ICoinIntegrationService coinService;

    @Autowired
    private IMapper<CoinResponseDTO, Mono<CoinDTO>> coinDTOMapper;

    @Autowired
    private IMapper<List<CoinResponseDTO>, Flux<CoinDTO>> coinDTOManyMapper;

    @Override
    public List<CoinResponseDTO> list() {
        return coinProvider.getAllCoins();
    }

    @Override
    public Mono<CoinMetadataDTO> getMetadata(String coinID) {
        return coinService.getInfo(coinID);
    }

    @Override
    public Flux<CoinMetadataDTO> getMetadata(String... coinIDs) {
        return coinService.getAllInfos(coinIDs);
    }

    @Override
    public Flux<CoinDTO> getAll() {
        return coinDTOManyMapper.mapToDto(coinProvider.getAllCoins());
    }

    @Override
    public Flux<CoinDTO> getAll(String ids) {
        return coinDTOManyMapper.mapToDto(coinProvider.getCoins(ids));
    }

    @Override
    public Mono<CoinDTO> getById(String coinID) {
        return coinDTOMapper.mapToDto(coinProvider.getCoinById(coinID));
    }

    @Override
    public Mono<CoinDTO> getByRank(int rank) {
        return coinDTOMapper.mapToDto(coinProvider.getByRank(rank));
    }

    @Override
    public Mono<CoinDTO> getBySymbol(String symbol) {
        return coinDTOMapper.mapToDto(coinProvider.getBySymbol(symbol));
    }

    @Override
    public Mono<CoinDTO> getByName(String name) {
        return coinDTOMapper.mapToDto(coinProvider.getCoinByName(name));
    }

    @Override
    public Flux<HistoryInfoDTO> getAllHistory(String id, String vs_currency, String days, Optional<String> interval) {
        if (!interval.isPresent())
            return coinService.getHistoryOfCoin(id, vs_currency, days);

        return coinService.getHistoryOfCoin(id, vs_currency, days, interval.get());
    }

    @Override
    public Flux<CandleInfoDTO> getAllCandles(String id, String vs_currency, String days) {
        return coinService.getCandleOfCoin(id, vs_currency, days);
    }


}
