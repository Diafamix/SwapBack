package com.tfg.swapCatBack.core.controllers.services.impl;


import com.tfg.swapCatBack.core.controllers.services.IAdminService;
import com.tfg.swapCatBack.data.entities.enums.UserRole;
import com.tfg.swapCatBack.data.entities.enums.UserType;
import com.tfg.swapCatBack.data.providers.ICoinProvider;
import com.tfg.swapCatBack.data.providers.IUserProvider;
import com.tfg.swapCatBack.dto.data.request.UserRegisterDTO;
import com.tfg.swapCatBack.dto.data.response.BannedUserResponseDTO;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
//import com.tfg.swapCatBack.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final ICoinProvider coinProvider;
    private final IUserProvider userProvider;
    //private final SecurityContextHelper securityContextHelper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userProvider.getAll();
    }

    @Override
    public CoinResponseDTO createCoin(String coinID, String name, String symbol) {
        return coinProvider.createCoin(coinID, name, symbol);
    }

    @Override
    public CoinResponseDTO deleteCoin(String name) {
        return coinProvider.deleteByName(name);
    }

    @Override
    public BannedUserResponseDTO banUser(String mail) {
        return userProvider.banUser(mail);
    }

    @Override
    public BannedUserResponseDTO unBanUser(String mail) {
        return userProvider.unBanUser(mail);
    }

    @Override
    public UserResponseDTO getUserById(long id) {
        return userProvider.getById(id);
    }

    @Override
    public UserResponseDTO createUser(String mail, String username, UserRole userRole, UserType userType) {
        return userProvider.register(UserRegisterDTO.builder()
                .mail(mail)
                .username(username)
                .role(userRole)
                .password("12345")
                .type(userType)
                .build());
    }

    @Override
    public UserResponseDTO changeUserType(String mail, UserType userType) {
        return userProvider.changeUserType(mail, userType);
    }


}
