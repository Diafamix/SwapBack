package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.entities.HistoryModel;
import com.tfg.swapCatBack.data.entities.UserModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.HistoryResponseDTO;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegisterResponseMapper implements IMapper<HistoryModel, HistoryResponseDTO> {

    private final ObjectMapper mapper;
    private final IMapper<UserModel, UserResponseDTO> userDto;

    @Override
    public HistoryResponseDTO mapToDto(HistoryModel model) {
        return HistoryResponseDTO.builder()
                .user(userDto.mapToDto(model.getUser()))
                .date(model.getDate())
                .origin(model.getOrigin())
                .destiny(model.getDestiny())
                .quantity(model.getQuantity())
                .portfolio(model.getPortfolio())
                .build();
    }

    @SneakyThrows
    @Override
    public HistoryModel mapToEntity(HistoryResponseDTO historyResponseDTO) {
        throw new UnsupportedOperationException();
    }
}
