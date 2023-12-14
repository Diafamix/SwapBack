package com.tfg.swapCatBack;

import com.tfg.swapCatBack.core.loaders.CoinLoader;
import com.tfg.swapCatBack.core.loaders.UsersLoader;
import com.tfg.swapCatBack.core.services.IPortfolioCalculator;
import com.tfg.swapCatBack.data.providers.IAccountProvider;
import com.tfg.swapCatBack.data.providers.IRegisterProvider;
import com.tfg.swapCatBack.integration.websocket.CoinCapConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SwapCatBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapCatBackApplication.class, args);
	}

	@Bean
	CommandLineRunner init(
			CoinLoader coinLoader,
			UsersLoader usersLoader,
			IAccountProvider accountProvider,
			IRegisterProvider registerProvider,
			CoinCapConsumer coinCapConsumer,
			IPortfolioCalculator portfolioCalculator
	) {
		return (args) -> {
			coinCapConsumer.start(); // Starts websocket

			coinLoader.load().blockLast();
			usersLoader.load().blockLast();

			mockHistoryAndPortfolio(accountProvider, registerProvider);
		};
	}

	private void mockHistoryAndPortfolio(IAccountProvider accountProvider, IRegisterProvider registerProvider) {

		accountProvider.deposit("carlos.cueva", "Bitcoin", 1);
		registerProvider.log("carlos.cueva",
				LocalDate.now().minusDays(10), "Test", "test2", 15);

		accountProvider.deposit("carlos.cueva", "Ethereum", 1);
		registerProvider.log("carlos.cueva",
				LocalDate.now().minusDays(5), "Test", "test2", 15);


	}
}
