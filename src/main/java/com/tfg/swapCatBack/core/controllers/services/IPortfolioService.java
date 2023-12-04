package com.tfg.swapCatBack.core.controllers.services;

import com.tfg.swapCatBack.dto.data.response.PortfolioResponseDTO;
import com.tfg.swapCatBack.dto.data.response.WalletResponseDto;
import com.tfg.swapCatBack.dto.integration.HistoryInfoDTO;

import java.util.List;

/**
 * This class represents all the relevant methods to get information about User and Account from the database.
 */
public interface IPortfolioService {

    WalletResponseDto get(String coin);

    PortfolioResponseDTO getAll();

    List<HistoryInfoDTO> getPortfolioChart();

}
