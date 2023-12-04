package com.tfg.swapCatBack.integration.adapters;

import com.tfg.swapCatBack.dto.integration.CoinHistoricalMarketDTO;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IHistoricalMarketAdapter {

    Mono<CoinHistoricalMarketDTO> getHistorical(String id, LocalDate dateAt);

}
