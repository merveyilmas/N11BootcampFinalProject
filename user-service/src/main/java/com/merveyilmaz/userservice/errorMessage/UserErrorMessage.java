package com.merveyilmaz.userservice.errorMessage;

import com.merveyilmaz.userservice.general.BaseErrorMessage;

public enum UserErrorMessage implements BaseErrorMessage {
    INVALID_OLD_PASSWORD("Invalid old password!");

    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
