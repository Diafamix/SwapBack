package com.tfg.swapCatBack.core.services;

import com.tfg.swapCatBack.dto.integration.HistoryInfoDTO;

import java.time.LocalDate;
import java.util.List;

public interface IPortfolioCalculator {

    List<HistoryInfoDTO> calculateAllTime(String username);

    List<HistoryInfoDTO> calculateInterval(String username, LocalDate start, LocalDate end);

}
