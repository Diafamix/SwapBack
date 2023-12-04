package com.tfg.swapCatBack.core.controllers.services.impl;

import com.tfg.swapCatBack.core.controllers.services.IStackingService;
import com.tfg.swapCatBack.data.providers.IRegisterProvider;
import com.tfg.swapCatBack.data.providers.IStackingProvider;
import com.tfg.swapCatBack.data.providers.IUserProvider;
import com.tfg.swapCatBack.dto.data.request.RegisterRequestDTO;
import com.tfg.swapCatBack.dto.data.response.StackingDTO;
import com.tfg.swapCatBack.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class StackingServiceImpl implements IStackingService {

    private final IUserProvider userProvider;
    private final IRegisterProvider registerProvider;

    private final IStackingProvider stackingProvider;
    private final SecurityContextHelper securityContextHelper;

    /**
     * Este metodo muestra una lista de todos los stakes
     *
     * @return Lista de stakes
     */
    @Override
    public List<StackingDTO> findAll() {
        return stackingProvider.findAll();
    }

    /**
     * Este metodo muestra todos los stakes de un usuario
     *
     * @return todos los stakes del usuario
     */
    @Override
    public List<StackingDTO> findAllByUser() {
        return stackingProvider.getAllUserStakes(securityContextHelper.getUser().getUsername()); //TODO Security
    }

    /**
     * Este metodo hace un Stake para un usuario
     *
     * @param coinName      nombre de la moneda
     * @param quantity      cantidad de dicha moneda
     * @param daysToExpires dias para que expire el stake
     * @return el stake creado
     */
    @Override
    public StackingDTO stake(String coinName, double quantity, int daysToExpires) {
        String userName = securityContextHelper.getUser().getUsername();
        StackingDTO stackingDTO = stackingProvider.stake(userName, coinName, quantity, daysToExpires);

        RegisterRequestDTO registerResponseDTO = RegisterRequestDTO.builder()
                .user(userName)
                .date(LocalDate.now())
                .quantity(quantity)
                .destiny("Staking id: " + stackingDTO.getId()) //TODO Security
                .origin("Wallet id: " + stackingDTO.getUser().username)
                .build();

        //registerProvider.log(registerResponseDTO);

        return stackingDTO;
    }

    /**
     * Este metodo borra un stake por su ID
     *
     * @param id       del stake
     * @return el stake que se ha borrado
     */
    @Override
    public StackingDTO unStake(long id) {
        String userName = securityContextHelper.getUser().getUsername();
        StackingDTO dto = stackingProvider.unStake(id, userName);

        RegisterRequestDTO registerResponseDTO = RegisterRequestDTO.builder()
                .user(userName)
                .origin("Staking id: " + dto.getId())
                .destiny("Wallet id: " + dto.getUser().username) //TODO MAP
                .build();

        //registerProvider.log(registerResponseDTO);

        return dto; //TODO Security
    }

    /**
     * Este metodo busca un stake de un usuario
     *
     * @param id       del stake
     * @return el stake buscado
     */
    @Override
    public StackingDTO findUserStakeById(long id) {

        return stackingProvider.getUserStake(id, securityContextHelper.getUser().getUsername());
    }
}
