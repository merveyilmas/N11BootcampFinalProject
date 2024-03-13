package com.merveyilmaz.logservice.service.entityService;

import com.merveyilmaz.logservice.dao.ErrorLogRepository;
import com.merveyilmaz.logservice.entity.ErrorLog;
import com.merveyilmaz.logservice.general.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogEntityService extends BaseEntityService<ErrorLog, ErrorLogRepository> {

    protected ErrorLogEntityService(ErrorLogRepository repository) {
        super(repository);
    }
}
