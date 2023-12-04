package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.PortfolioResponseDTO;
import com.tfg.swapCatBack.dto.data.response.WalletResponseDto;

public class CoinDetailsMapper implements IMapper<WalletResponseDto, PortfolioResponseDTO> {


    @Override
    public PortfolioResponseDTO mapToDto(WalletResponseDto walletResponseDto) {
        return null;
    }

    @Override
    public WalletResponseDto mapToEntity(PortfolioResponseDTO portfolioResponseDTO) {
        return null;
    }
}
