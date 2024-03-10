package com.merveyilmaz.logservice.dao;

import com.merveyilmaz.logservice.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository  extends JpaRepository<ErrorLog, Long> {
}
