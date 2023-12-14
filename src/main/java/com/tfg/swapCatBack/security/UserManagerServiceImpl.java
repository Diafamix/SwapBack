package com.tfg.swapCatBack.security;


import com.tfg.swapCatBack.data.providers.IUserProvider;
import com.tfg.swapCatBack.dto.data.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class UserManagerServiceImpl implements AuthenticationProvider {

    private final IUserProvider userProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println("-----------------" + name + "-----------------");
        System.out.println("-----------------" + password + "-----------------");

        if (!userProvider.existsByUsername(name) && !userProvider.matchesPasswordByUsername(name, password))
            throw new BadCredentialsException("");

        UserResponseDTO dto = userProvider.getByName(name);

        return new UsernamePasswordAuthenticationToken(dto,null, Collections.singletonList(dto.role::name));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
