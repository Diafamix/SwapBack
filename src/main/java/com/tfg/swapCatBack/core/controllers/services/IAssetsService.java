package com.tfg.swapCatBack.core.controllers.services;


import com.tfg.swapCatBack.dto.controller.CoinDTO;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.dto.integration.CandleInfoDTO;
import com.tfg.swapCatBack.dto.integration.CoinMetadataDTO;
import com.tfg.swapCatBack.dto.integration.HistoryInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * This class represents all the relevant methods to get information about User and Account from the database.
 */

public interface IAssetsService {

    Flux<CoinDTO> getAll();

    Flux<CoinDTO> getAll(String ids);

}
