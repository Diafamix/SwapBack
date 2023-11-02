package com.tfg.swapCatBack.integration.adapters.impl;


import com.tfg.swapCatBack.dto.integration.CoinMetadataDTO;
import com.tfg.swapCatBack.integration.adapters.ICoinInfoAdapter;
import com.tfg.swapCatBack.integration.adapters.mappers.AdapterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinInfoAdapterImpl implements ICoinInfoAdapter {

    private static final WebClient webclient = WebClient.builder()
            .baseUrl("https://api.coingecko.com/api/v3/coins/")
            .build();

    private final AdapterMapper<CoinMetadataDTO> mapper;

    @Override
    public Mono<CoinMetadataDTO> get(String coinID, CoinInfoOptions options) {
        return webclient.get()
                .uri(builder -> {
                    builder.path(coinID);
                    return options.queryParams(builder);
                })
                .retrieve()
                .bodyToMono(String.class)
                .map(mapper::mapToDto);
    }

}
