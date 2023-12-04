package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.entities.CoinModel;
import com.tfg.swapCatBack.data.entities.StackingModel;
import com.tfg.swapCatBack.data.entities.UserModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.dto.data.response.StackingDTO;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StackingResponseMapper implements IMapper<StackingModel, StackingDTO> {

    private final IMapper<UserModel, UserResponseDTO> userMapper;
    private final IMapper<CoinModel, CoinResponseDTO> coinMapper;

    @Override
    public StackingDTO mapToDto(StackingModel stackingModel) {
        return StackingDTO.builder()
                .user(userMapper.mapToDto(stackingModel.getUser()))
                .coin(coinMapper.mapToDto(stackingModel.getCoin()))
                .quantity(stackingModel.getQuantity())
                .createdAt(stackingModel.getCreatedAt())
                .daysToExpire(stackingModel.getDaysToExpire())
                .build();
    }

    @Override
    public StackingModel mapToEntity(StackingDTO stackingDTO) {
        throw new UnsupportedOperationException();
    }
}
