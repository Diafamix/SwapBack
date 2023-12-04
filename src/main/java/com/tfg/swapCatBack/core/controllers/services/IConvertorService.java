package com.tfg.swapCatBack.core.controllers.services;

import com.tfg.swapCatBack.dto.integration.ConversorDTO;
import reactor.core.publisher.Mono;

public interface IConvertorService {

    /**
     * Convert the given amount of a coin to USD
     *
     * @param from   the coin to be converted
     * @param amount the amount to convert
     * @return the dto with the information
     */
    Mono<ConversorDTO> convert(String from, double amount);

    /**
     * Converts a given amount of a coin to a target coin
     *
     * @param from   the coin to be converted
     * @param to     the coin to convert to
     * @param amount the amount to convert
     * @return the dto with the information
     */
    Mono<ConversorDTO> convert(String from, String to, double amount);

}
