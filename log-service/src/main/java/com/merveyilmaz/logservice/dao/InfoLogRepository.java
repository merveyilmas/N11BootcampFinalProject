package com.merveyilmaz.logservice.dao;

import com.merveyilmaz.logservice.entity.InfoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoLogRepository extends JpaRepository<InfoLog, Long> {
}
