package com.tfg.swapCatBack;

import com.tfg.swapCatBack.core.loaders.CoinLoader;
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
			CoinCapConsumer coinCapConsumer
	) {
		return (args) -> {
			coinCapConsumer.start(); // Starts websocket

			coinLoader.load().blockLast();

		};
	}

	private void callBack() {
	}

}
