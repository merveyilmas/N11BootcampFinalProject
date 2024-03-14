package com.merveyilmaz.userservice.exceptions;

import com.merveyilmaz.userservice.general.BaseErrorMessage;
import com.merveyilmaz.userservice.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyListException extends BusinessException {

    public EmptyListException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
