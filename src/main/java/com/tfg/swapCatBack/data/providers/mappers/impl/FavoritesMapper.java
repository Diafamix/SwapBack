package com.tfg.swapCatBack.data.providers.mappers.impl;

import com.tfg.swapCatBack.data.entities.CoinModel;
import com.tfg.swapCatBack.data.entities.FavouritesModel;
import com.tfg.swapCatBack.data.entities.UserModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.FavoritesResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FavoritesMapper implements IMapper<FavouritesModel, FavoritesResponseDto> {

    @Override
    public FavoritesResponseDto mapToDto(FavouritesModel favouritesModel) {
        return FavoritesResponseDto.builder()
                .id(favouritesModel.getId())
                .userName(favouritesModel.getUser().getUsername())
                .coinName(favouritesModel.getCoin().getCoinID())
                .build();
    }

    @Override
    public FavouritesModel mapToEntity(FavoritesResponseDto favoritesResponseDto) {
        CoinModel coin = CoinModel.builder()
                .name(favoritesResponseDto.getCoinName())
                .build();

        UserModel user = UserModel.builder()
                .username(favoritesResponseDto.getUserName())
                .build();

        return FavouritesModel.builder()
                .coin(coin)
                .user(user)
                .build();
    }
}
