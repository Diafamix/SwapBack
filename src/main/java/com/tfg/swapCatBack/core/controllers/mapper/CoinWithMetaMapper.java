package com.tfg.swapCatBack.core.controllers.mapper;

import com.tfg.swapCatBack.core.utils.MapperFactoryService;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.controller.CoinDTO;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.dto.integration.CoinMarketDTO;
import com.tfg.swapCatBack.integration.services.ICoinIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CoinWithMetaMapper implements IMapper<CoinResponseDTO, Mono<CoinDTO>> {

    @Autowired
    @Qualifier("Cache")
    private ICoinIntegrationService coinService;

    @Override
    public Mono<CoinDTO> mapToDto(CoinResponseDTO coinResponseDTO) {
        return coinService.getAllMarketByIds(coinResponseDTO.coinID)
                .single()
                .map(coinMetadataDTO -> mergeCoinAndMetadata(coinResponseDTO, coinMetadataDTO));
    }

    @Override
    public CoinResponseDTO mapToEntity(Mono<CoinDTO> coinDtoMono) {
        throw new UnsupportedOperationException();
    }

    private CoinDTO mergeCoinAndMetadata(CoinResponseDTO responseDTO, CoinMarketDTO marketDTO) {
        CoinDTO.CoinMarketDTO marketControllerDTO = new CoinDTO.CoinMarketDTO();
        MapperFactoryService.doMap(marketDTO, marketControllerDTO);

        return CoinDTO.builder()
                .id(responseDTO.coinID)
                .name(responseDTO.name)
                .symbol(responseDTO.symbol)
                .rank(responseDTO.id)
                .marketData(marketControllerDTO)
                .build();
    }

}
