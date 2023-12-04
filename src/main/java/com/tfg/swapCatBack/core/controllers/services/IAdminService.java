package com.tfg.swapCatBack.core.controllers.services;

import com.tfg.swapCatBack.data.entities.enums.UserRole;
import com.tfg.swapCatBack.data.entities.enums.UserType;
import com.tfg.swapCatBack.dto.data.response.BannedUserResponseDTO;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;

import java.util.List;

public interface IAdminService {

    List<UserResponseDTO> getAllUsers();

    CoinResponseDTO createCoin(String coinID, String name, String symbol);

    CoinResponseDTO deleteCoin(String name);

    BannedUserResponseDTO banUser(String mail);

    BannedUserResponseDTO unBanUser(String mail);

    UserResponseDTO getUserById(long id);

    UserResponseDTO createUser(String mail, String username, UserRole userRole, UserType userType);

    UserResponseDTO changeUserType(String mail, UserType userType);

}
