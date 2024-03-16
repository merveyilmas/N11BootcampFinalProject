package com.merveyilmaz.userservice.general;

public enum GeneralErrorMessage implements BaseErrorMessage{
    ITEM_NOT_FOUND("Item not found!"),
    EMPTY_LIST("Empty list!"),
    NULL_DATA_RECEIVED("Null data received!");

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
