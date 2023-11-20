package com.tfg.swapCatBack.integration.websocket;

import org.springframework.context.ApplicationEvent;

public class CoinCapPriceUpdateEvent extends ApplicationEvent {

    private final String coin;
    private final double price;

    public CoinCapPriceUpdateEvent(Object source, String coin, double price) {
        super(source);
        this.coin = coin;
        this.price = price;
    }

    public String getCoin() {
        return coin;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CoinCapPriceUpdateEvent{" +
                "coin='" + coin + '\'' +
                ", price=" + price +
                '}';
    }
}