package com.merveyilmaz.userservice.general;

public enum GeneralErrorMessage implements BaseErrorMessage{
    ITEM_NOT_FOUND("Item not found!"),
    ACTIVE_ITEM_NOT_FOUND("Active item not found!");

    private final String message;

    GeneralErrorMessage(String message) {
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
