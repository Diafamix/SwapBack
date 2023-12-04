package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.entities.RestartModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.RestarResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestartResponseMapper implements IMapper<RestartModel, RestarResponseDTO> {
    @Override
    public RestarResponseDTO mapToDto(RestartModel restartModel) {
        return RestarResponseDTO.builder()
                .month(restartModel.getMonth())
                .year(restartModel.getYear())
                .build();
    }

    @Override
    public RestartModel mapToEntity(RestarResponseDTO restarResponseDTO) {
        return RestartModel.builder()
                .month(restarResponseDTO.getMonth())
                .year(restarResponseDTO.getYear())
                .build();
    }
}
