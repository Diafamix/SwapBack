package com.tfg.swapCatBack.dto.data.response;

import com.tfg.swapCatBack.dto.integration.CoinMarketDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinDetailsDTO {

    private long id;
    private String name;
    private double quantity;
    private double allocation;
    private CoinMarketDTO marketData;

}
