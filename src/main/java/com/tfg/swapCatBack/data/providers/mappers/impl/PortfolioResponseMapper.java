package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.CoinDetailsDTO;
import com.tfg.swapCatBack.dto.data.response.PortfolioResponseDTO;
import com.tfg.swapCatBack.dto.data.response.WalletResponseDto;
import com.tfg.swapCatBack.integration.services.ICoinIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PortfolioResponseMapper implements IMapper<Map<String, WalletResponseDto>, PortfolioResponseDTO> {

    @Autowired
    @Qualifier("Cache")
    private ICoinIntegrationService coinIntegrationService;

    @Override
    public PortfolioResponseDTO mapToDto(Map<String, WalletResponseDto> wallets) {
        List<CoinDetailsDTO> coinDetailsDTOList = wallets.values().stream()
                .map(this::mapToDetails)
                .collect(Collectors.toList());

        double totalBalance = calculateBalance(coinDetailsDTOList);
        calculateAllocation(coinDetailsDTOList, totalBalance);

        return PortfolioResponseDTO.builder()
                .balance(totalBalance)
                .wallets(sortedWallets(coinDetailsDTOList))
                .build();
    }

    private List<CoinDetailsDTO> sortedWallets(List<CoinDetailsDTO> detailsDTOS) {
        return detailsDTOS.stream()
                .sorted(Comparator.comparing(CoinDetailsDTO::getAllocation).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, WalletResponseDto> mapToEntity(PortfolioResponseDTO portfolioResponseDTO) {
        throw new UnsupportedOperationException();
    }

    private CoinDetailsDTO mapToDetails(WalletResponseDto dto) {
        return CoinDetailsDTO.builder()
                .id(dto.getId())
                .name(dto.getCoinName())
                .quantity(dto.getQuantity())
                .marketData(coinIntegrationService.getAllMarkets("usd", dto.getCoinName()).blockFirst())
                .build();
    }

    public double calculateBalance(List<CoinDetailsDTO> coinDetailsDTOList) {
        return coinDetailsDTOList.stream()
                .map(coinDetailsDTO -> coinDetailsDTO.getQuantity() * coinDetailsDTO.getMarketData().getCurrent_price())
                .reduce(Double::sum)
                .orElse(0.0);

    }

    private void calculateAllocation(List<CoinDetailsDTO> coinDetailsDTOList, double totalBalance) {
        for (CoinDetailsDTO coinDetailsDTO : coinDetailsDTOList) {
            double individualPrice = coinDetailsDTO.getQuantity() * coinDetailsDTO.getMarketData().getCurrent_price();
            double allocation = (individualPrice / totalBalance) * 100;

            coinDetailsDTO.setAllocation(allocation);
        }
    }
}
