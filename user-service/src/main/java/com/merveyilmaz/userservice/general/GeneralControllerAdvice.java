package com.merveyilmaz.userservice.general;


import com.merveyilmaz.userservice.exceptions.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GeneralControllerAdvice {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {

        String message = e.getMessage();
        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRTExceptions(ItemNotFoundException e, WebRequest request) {

        String message = e.getBaseErrorMessage().getMessage();
        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRTExceptions(BusinessException e, WebRequest request) {

        String message = e.getBaseErrorMessage().getMessage();
        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }
}
