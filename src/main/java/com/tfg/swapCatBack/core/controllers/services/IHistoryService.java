package com.tfg.swapCatBack.core.controllers.services;

import com.tfg.swapCatBack.dto.data.response.HistoryResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHistoryService {

    List<HistoryResponseDTO> getAllRegisterUser(LocalDate start, LocalDate end);

    HistoryResponseDTO getOneRegister(long id);
}
