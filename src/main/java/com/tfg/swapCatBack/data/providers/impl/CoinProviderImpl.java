package com.tfg.swapCatBack.data.providers.impl;


import com.tfg.swapCatBack.data.daos.ICoinDAO;
import com.tfg.swapCatBack.data.providers.ICoinProvider;
import com.tfg.swapCatBack.data.entities.CoinModel;
import com.tfg.swapCatBack.data.providers.mappers.IMapper;
import com.tfg.swapCatBack.dto.data.response.CoinResponseDTO;
import com.tfg.swapCatBack.exceptions.data.CoinAlreadyExistsException;
import com.tfg.swapCatBack.exceptions.data.CoinNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CoinProviderImpl implements ICoinProvider {

    private static final String NO_COIN_FOUND = "The coin %s is not supported";
    private static final String COIN_ALREADY_EXISTS = "The coin %s already exists!";

    private final ICoinDAO coinDAO;
    private final IMapper<CoinModel, CoinResponseDTO> responseDTOIMapper;

    @Override
    public synchronized CoinResponseDTO createCoin(String coinID, String name, String symbol) {
        if (coinDAO.findByName(name).isPresent())
            throw new CoinAlreadyExistsException(String.format(COIN_ALREADY_EXISTS, name));

        CoinModel coin = CoinModel.builder()
                .coinID(coinID)
                .name(name)
                .symbol(symbol)
                .build();

        coin = coinDAO.save(coin);

        return responseDTOIMapper.mapToDto(coin);
    }

    @Override
    public synchronized List<CoinResponseDTO> getAllCoins() {
        return coinDAO.findAll().stream()
                .map(responseDTOIMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoinResponseDTO> getCoins(String... ids) {
        Set<String> idsToSearch = Arrays.stream(ids).collect(Collectors.toSet());

        return getAllCoins().stream()
                .filter(responseDTO -> idsToSearch.contains(responseDTO.coinID))
                .collect(Collectors.toList());
    }

    @Override
    //@Transactional
    public CoinResponseDTO deleteByName(String name) {
        CoinModel coin = coinDAO.findByName(name)
                .orElseThrow(() -> new CoinNotFoundException(String.format(NO_COIN_FOUND, name)));

        coinDAO.deleteByName(name);

        return responseDTOIMapper.mapToDto(coin);
    }

    @Override
    public synchronized CoinResponseDTO getCoinByName(String name) {
        return coinDAO.findByName(name)
                .map(responseDTOIMapper::mapToDto)
                .orElseThrow(() -> new CoinNotFoundException(String.format(NO_COIN_FOUND, name)));
    }

    @Override
    public synchronized CoinResponseDTO getCoinById(String coinID) {
        return coinDAO.findByCoinID(coinID)
                .map(responseDTOIMapper::mapToDto)
                .orElseThrow(() -> new CoinNotFoundException(String.format(NO_COIN_FOUND, coinID)));
    }

    @Override
    public synchronized CoinResponseDTO getByRank(int rank) {
        return coinDAO.findByRank(rank)
                .map(responseDTOIMapper::mapToDto)
                .orElseThrow(() -> new CoinNotFoundException(String.format(NO_COIN_FOUND, rank)));
    }

    @Override
    public synchronized CoinResponseDTO getBySymbol(String symbol) {
        return coinDAO.findBySymbol(symbol)
                .map(responseDTOIMapper::mapToDto)
                .orElseThrow(() -> new CoinNotFoundException(String.format(NO_COIN_FOUND, symbol)));
    }

}
