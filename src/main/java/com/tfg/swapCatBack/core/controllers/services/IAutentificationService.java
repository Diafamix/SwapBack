package com.tfg.swapCatBack.core.controllers.services;

import com.tfg.swapCatBack.dto.data.request.UserRegisterDTO;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;

public interface IAutentificationService {

    UserResponseDTO register(UserRegisterDTO userRegisterDTO);

    boolean login(String username, String password);

    boolean loginv2(String mail, String password);

    UserResponseDTO retrieve(String email);

}
