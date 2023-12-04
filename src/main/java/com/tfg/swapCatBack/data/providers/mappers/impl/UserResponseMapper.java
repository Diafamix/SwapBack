package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.entities.FavouritesModel;
import com.tfg.swapCatBack.data.entities.UserModel;
import com.tfg.swapCatBack.data.entities.WalletModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.FavoritesResponseDto;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
import com.tfg.swapCatBack.dto.data.response.WalletResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserResponseMapper implements IMapper<UserModel, UserResponseDTO> {

    private final IMapper<FavouritesModel, FavoritesResponseDto> favouritesMapper;
    private final IMapper<WalletModel, WalletResponseDto> walletMapper;

    @Override
    public UserResponseDTO mapToDto(UserModel userModel) {
        Map<String, WalletResponseDto> walletMap = new HashMap<>();

        userModel.getWallets().forEach(walletModel ->
                walletMap.put(walletModel.getCoin().getCoinID(), walletMapper.mapToDto(walletModel))
        );

        List<FavoritesResponseDto> favoritesResponseDtos = userModel.getFavourites().stream()
                .map(favouritesMapper::mapToDto)
                .collect(Collectors.toList());

        return UserResponseDTO.builder()
                .mail(userModel.getMail())
                .username(userModel.getUsername())
                .role(userModel.getRole())
                .type(userModel.getType())
                .favorites(favoritesResponseDtos)
                .wallet(walletMap)
                .numRequests(userModel.getNumRequests())
                .build();
    }

    @Override
    public UserModel mapToEntity(UserResponseDTO userResponseDTO) {
        throw new UnsupportedOperationException();
    }
}
