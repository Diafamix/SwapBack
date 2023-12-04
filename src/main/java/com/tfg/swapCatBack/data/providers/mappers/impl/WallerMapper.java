package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.entities.WalletModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.WalletResponseDto;
import org.springframework.stereotype.Component;

@Component
public class WallerMapper implements IMapper<WalletModel, WalletResponseDto> {

    @Override
    public WalletResponseDto mapToDto(WalletModel walletModel) {
        return WalletResponseDto.builder()
                .id(walletModel.getId())
                .coinName(walletModel.getCoin().getCoinID())
                .quantity(walletModel.getQuantity())
                .build();
    }

    @Override
    public WalletModel mapToEntity(WalletResponseDto walletResponseDto) {
        throw new RuntimeException();
    }
}
