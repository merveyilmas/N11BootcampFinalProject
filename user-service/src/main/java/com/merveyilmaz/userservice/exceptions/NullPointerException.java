package com.merveyilmaz.userservice.exceptions;

import com.merveyilmaz.userservice.general.BaseErrorMessage;
import com.merveyilmaz.userservice.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullPointerException extends BusinessException {
    public NullPointerException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
