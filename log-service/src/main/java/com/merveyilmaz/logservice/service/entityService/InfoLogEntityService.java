package com.merveyilmaz.logservice.service.entityService;

import com.merveyilmaz.logservice.dao.InfoLogRepository;
import com.merveyilmaz.logservice.entity.InfoLog;
import com.merveyilmaz.logservice.general.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class InfoLogEntityService extends BaseEntityService<InfoLog, InfoLogRepository> {

    protected InfoLogEntityService(InfoLogRepository repository) {
        super(repository);
    }
}
