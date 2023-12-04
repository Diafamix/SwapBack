package com.tfg.swapCatBack.core.controllers.services.impl;

import com.tfg.swapCatBack.core.controllers.services.IAssetsService;
import com.tfg.swapCatBack.data.providers.ICoinProvider;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.controller.CoinDTO;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetsServiceImpl implements IAssetsService {

    private final ICoinProvider coinProvider;

    private final IMapper<List<CoinResponseDTO>, Flux<CoinDTO>> coinDTOManyMapper;

    @Override
    public Flux<CoinDTO> getAll() {
        return coinDTOManyMapper.mapToDto(coinProvider.getAllCoins());
    }

    @Override
    public Flux<CoinDTO> getAll(String ids) {
        return coinDTOManyMapper.mapToDto(coinProvider.getCoins(ids));
    }

}
