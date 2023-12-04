package com.tfg.swapCatBack.exceptions.data;

import com.tfg.swapCatBack.exceptions.LogicError;

public class FavoritesNotFoundException extends LogicError {

    public FavoritesNotFoundException() {
    }

    public FavoritesNotFoundException(String message) {
        super(message);
    }

    public FavoritesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FavoritesNotFoundException(Throwable cause) {
        super(cause);
    }

    public FavoritesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
