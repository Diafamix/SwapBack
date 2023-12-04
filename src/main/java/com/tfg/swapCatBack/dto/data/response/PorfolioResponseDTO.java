package com.tfg.swapCatBack.dto.data.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PorfolioResponseDTO {

    private double balance ;
    private final List<CoinDetailsDTO> coinDetailsDTOList;




}
