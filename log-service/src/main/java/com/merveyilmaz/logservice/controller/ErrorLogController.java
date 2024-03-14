package com.merveyilmaz.logservice.controller;

import com.merveyilmaz.logservice.entity.ErrorLog;
import com.merveyilmaz.logservice.entity.InfoLog;
import com.merveyilmaz.logservice.general.RestResponse;
import com.merveyilmaz.logservice.service.entityService.ErrorLogEntityService;
import com.merveyilmaz.logservice.service.entityService.InfoLogEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/error-logs")
@RequiredArgsConstructor
public class ErrorLogController {

    private final ErrorLogEntityService errorLogEntityService;

    @GetMapping
    public ResponseEntity<RestResponse<List<ErrorLog>>> getAllErrorLogs() {

        List<ErrorLog> errorLogs = errorLogEntityService.findAll();
        return ResponseEntity.ok(RestResponse.of(errorLogs));
    }
}
