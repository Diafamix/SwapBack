package com.tfg.swapCatBack.core.controllers.services.impl;

import com.tfg.swapCatBack.core.controllers.services.IPortfolioService;
import com.tfg.swapCatBack.core.services.IPortfolioCalculator;
import com.tfg.swapCatBack.data.providers.IAccountProvider;
import com.tfg.swapCatBack.data.providers.IUserProvider;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.PortfolioResponseDTO;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
import com.tfg.swapCatBack.dto.data.response.WalletResponseDto;
import com.tfg.swapCatBack.dto.integration.HistoryInfoDTO;
import com.tfg.swapCatBack.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PortfolioServiceImpl implements IPortfolioService {

    private final IAccountProvider accountProvider;
    private final SecurityContextHelper securityContextHelper;
    private final IPortfolioCalculator portfolioCalculator;
    private final IUserProvider userProvider;

    private final IMapper<Map<String, WalletResponseDto>, PortfolioResponseDTO> mapper;

    @Override
    public WalletResponseDto get(String coin) {
        return accountProvider.get(securityContextHelper.getUser().getUsername(), coin);
        //return accountProvider.get("carlos.cueva", coin);
    }

    @Override
    public PortfolioResponseDTO getAll() {
        UserResponseDTO userResponseDTO = securityContextHelper.getUser();
       //UserResponseDTO userResponseDTO = userProvider.getByName("carlos.cueva");
        Map<String, WalletResponseDto> wallets = userResponseDTO.getWallet();

        return mapper.mapToDto(wallets);
    }

    @Override
    public List<HistoryInfoDTO> getPortfolioChart() {
        return portfolioCalculator.calculateAllTime(securityContextHelper.getUser().username);
        //return portfolioCalculator.calculateAllTime(userProvider.getByName("carlos.cueva").username);

    }


}
