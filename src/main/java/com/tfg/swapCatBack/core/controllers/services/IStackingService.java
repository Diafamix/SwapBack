package com.tfg.swapCatBack.core.controllers.services;

import com.tfg.swapCatBack.dto.data.response.StackingDTO;

import java.util.List;

public interface IStackingService {

    List<StackingDTO> findAll();

    List<StackingDTO> findAllByUser();

    StackingDTO stake(String coiname, double quantity, int daysToExpire);

    StackingDTO unStake(long id);

    StackingDTO findUserStakeById(long id);
}
