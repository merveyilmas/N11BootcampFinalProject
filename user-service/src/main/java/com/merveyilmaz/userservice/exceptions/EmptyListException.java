package com.merveyilmaz.userservice.exceptions;

import com.merveyilmaz.userservice.general.BaseErrorMessage;
import com.merveyilmaz.userservice.general.BusinessException;

public class EmptyListException extends BusinessException {

    public EmptyListException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
