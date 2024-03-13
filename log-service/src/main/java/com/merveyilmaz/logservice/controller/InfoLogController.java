package com.merveyilmaz.logservice.controller;

import com.merveyilmaz.logservice.entity.InfoLog;
import com.merveyilmaz.logservice.general.RestResponse;
import com.merveyilmaz.logservice.service.entityService.InfoLogEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/info-logs")
@RequiredArgsConstructor
public class InfoLogController {

    private final InfoLogEntityService infoLogEntityService;

    @GetMapping()
    public ResponseEntity<RestResponse<List<InfoLog>>> getAllInfoLogs() {

        List<InfoLog> infoLogs = infoLogEntityService.findAll();
        return ResponseEntity.ok(RestResponse.of(infoLogs));
    }

}
