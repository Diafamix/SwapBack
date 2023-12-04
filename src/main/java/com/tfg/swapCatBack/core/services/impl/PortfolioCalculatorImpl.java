package com.tfg.swapCatBack.core.services.impl;

import com.tfg.swapCatBack.core.services.IPortfolioCalculator;
import com.tfg.swapCatBack.data.providers.IRegisterProvider;
import com.tfg.swapCatBack.dto.data.response.HistoryResponseDTO;
import com.tfg.swapCatBack.dto.data.response.WalletResponseDto;
import com.tfg.swapCatBack.dto.integration.CoinHistoricalMarketDTO;
import com.tfg.swapCatBack.dto.integration.HistoryInfoDTO;
import com.tfg.swapCatBack.integration.services.ICoinIntegrationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
public class PortfolioCalculatorImpl implements IPortfolioCalculator {

    private static final double MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final TypeReference<List<WalletResponseDto>> PORTFOLIO_TYPE =
            new TypeReference<List<WalletResponseDto>>() {
            };

    @Autowired
    private IRegisterProvider registerProvider;

    @Autowired
    @Qualifier("Cache")
    private ICoinIntegrationService coinIntegrationService;

    @Override
    public List<HistoryInfoDTO> calculateAllTime(String username) {

        List<HistoryResponseDTO> histories = registerProvider.getAllLogsFromUser(username).stream()
                .sorted(Comparator.comparing(HistoryResponseDTO::getDate))
                .collect(Collectors.toList());      // Sorts by date

        if (histories.isEmpty()) return new ArrayList<>();

        LocalDate firstDate = histories.get(0).date;
        List<HistoryInfoDTO> chartList = getGranularityList(firstDate, LocalDate.now());

        int currentIndex = 0;
        HistoryEntry current = HistoryEntry.of(histories.get(currentIndex));

        for (ListIterator<HistoryInfoDTO> iterator = chartList.listIterator(); iterator.hasNext(); ) {

            HistoryInfoDTO historyInfoDTO = iterator.next();
            while ((currentIndex + 1) < histories.size()
                    && historyInfoDTO.time >= histories.get(currentIndex + 1).date.atStartOfDay(UTC).toEpochSecond() * 1000) {
                current = HistoryEntry.of(histories.get(++currentIndex));
            }

            double totalPrice = 0.0;
            for (WalletResponseDto wallet : current.wallets) {
                CoinHistoricalMarketDTO historicalDTO = coinIntegrationService
                        .getHistorical(wallet.getCoinName(), parseEpochTimeToLocalDate(historyInfoDTO.time))
                        .block();

                totalPrice += (historicalDTO.price * wallet.getQuantity());
            }

            iterator.set(new HistoryInfoDTO(totalPrice, historyInfoDTO.time));
        }

        return chartList;
    }

    @Override
    public List<HistoryInfoDTO> calculateInterval(String username, LocalDate start, LocalDate end) {
        throw new UnsupportedOperationException(); //TODO
    }

    private List<HistoryInfoDTO> getGranularityList(LocalDate start, LocalDate end) {

        long epochStart = start.atStartOfDay(UTC).toEpochSecond() * 1000;
        long epochEnd = end.atStartOfDay(UTC).toEpochSecond() * 1000;

        long diffOnMillis = (epochEnd - epochStart);
        int jump = (int) Math.ceil(diffOnMillis / MILLIS_IN_A_DAY) + 1;

        long currentTime = epochStart;
        List<HistoryInfoDTO> toReturn = new ArrayList<>(jump);
        for (int i = 0; i < jump; i++) {
            toReturn.add(new HistoryInfoDTO(0.0, currentTime));
            currentTime += MILLIS_IN_A_DAY;
        }

        return toReturn;
    }

    private LocalDate parseEpochTimeToLocalDate(long epochTime) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochTime), ZoneId.systemDefault()).toLocalDate();
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    private static final class HistoryEntry {

        private static final ObjectMapper jsonMapper = new ObjectMapper();

        public static HistoryEntry of(HistoryResponseDTO dto) {
            return new HistoryEntry(dto.date, parsePortfolioStr(dto.portfolio));
        }

        @SneakyThrows
        private static List<WalletResponseDto> parsePortfolioStr(String str) {
            return jsonMapper.readerFor(PORTFOLIO_TYPE).readValue(str);
        }

        public final LocalDate date;
        public final List<WalletResponseDto> wallets;

    }

}
