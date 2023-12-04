package com.tfg.swapCatBack.data.daos;


import com.tfg.swapCatBack.data.entities.BannedUsersModel;
import com.tfg.swapCatBack.data.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBannedUserDao extends JpaRepository<BannedUsersModel, UserModel> {

    Optional<BannedUsersModel> findByUserId(long id);

    Optional<BannedUsersModel> findByUserMail(String mail);

    Optional<BannedUsersModel> findByUser_Username(String username);

}
