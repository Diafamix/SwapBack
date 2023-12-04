package com.tfg.swapCatBack.exceptions.data;

import com.tfg.swapCatBack.exceptions.LogicError;

public class StackingNotFoundException extends LogicError {

    public StackingNotFoundException() {
    }

    public StackingNotFoundException(String message) {
        super(message);
    }

    public StackingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StackingNotFoundException(Throwable cause) {
        super(cause);
    }

    public StackingNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
