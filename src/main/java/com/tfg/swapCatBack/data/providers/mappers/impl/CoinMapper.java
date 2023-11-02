package com.tfg.swapCatBack.data.providers.mappers.impl;


import com.tfg.swapCatBack.data.entities.CoinModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CoinMapper implements IMapper<CoinModel, CoinResponseDTO> {

    @Override
    public CoinResponseDTO mapToDto(CoinModel coinModel) {
       return CoinResponseDTO.builder()
               .id(coinModel.getId())
               .coinID(coinModel.getCoinID())
               .name(coinModel.getName())
               .symbol(coinModel.getSymbol())
               .build();
    }

    @Override
    public CoinModel mapToEntity(CoinResponseDTO coinResponseDTO) {
        throw new UnsupportedOperationException();
    }
}
