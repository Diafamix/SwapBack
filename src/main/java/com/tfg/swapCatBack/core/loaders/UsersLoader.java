package com.tfg.swapCatBack.core.loaders;

import com.tfg.swapCatBack.data.entities.enums.UserRole;
import com.tfg.swapCatBack.data.entities.enums.UserType;
import com.tfg.swapCatBack.data.providers.IUserProvider;
import com.tfg.swapCatBack.dto.data.request.UserRegisterDTO;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersLoader {

    private final IUserProvider userProvider;

    public Flux<UserResponseDTO> load() {

        List<UserRegisterDTO> users = Arrays.asList(
                new UserRegisterDTO("test@gmail.com", "Front-admin", "1234", UserRole.ADMIN, UserType.PREMIUM_PLUS),
                new UserRegisterDTO("carlos.cueva@gmail.com", "carlos.cueva", "1234567", UserRole.USER, UserType.FREE));

        return Flux.fromIterable(users)
                .map(userProvider::register);
    }

}
