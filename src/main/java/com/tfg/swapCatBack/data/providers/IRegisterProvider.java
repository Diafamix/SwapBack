package com.tfg.swapCatBack.data.providers;

import com.tfg.swapCatBack.dto.data.request.RegisterRequestDTO;
import com.tfg.swapCatBack.dto.data.response.HistoryResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface IRegisterProvider {

    default HistoryResponseDTO log(RegisterRequestDTO dto) {
        return log(dto.user, dto.date, dto.origin, dto.destiny, dto.quantity);
    }

    HistoryResponseDTO log(String username, LocalDate date, String origin, String destiny, double quantity);

    List<HistoryResponseDTO> getAllLogsFromUser(String user);

    List<HistoryResponseDTO> getLogsFromUsers(String user, LocalDate start, LocalDate end);

    HistoryResponseDTO getOneRegister(long id);

}
