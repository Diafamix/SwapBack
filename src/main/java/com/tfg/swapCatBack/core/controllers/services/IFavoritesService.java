package com.tfg.swapCatBack.core.controllers.services;

import com.tfg.swapCatBack.dto.data.response.FavoritesResponseDto;

import java.util.List;

public interface IFavoritesService {
    List<FavoritesResponseDto> getByName();

    FavoritesResponseDto delete(String coin);

    FavoritesResponseDto create(String coin);

}
