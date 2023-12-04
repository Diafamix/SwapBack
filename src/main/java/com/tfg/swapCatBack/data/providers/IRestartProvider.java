package com.tfg.swapCatBack.data.providers;

import com.tfg.swapCatBack.dto.data.response.RestarResponseDTO;

public interface IRestartProvider {

    RestarResponseDTO findRestart(String month, String year);

    RestarResponseDTO newRestart(RestarResponseDTO restarResponseDTO);
}
