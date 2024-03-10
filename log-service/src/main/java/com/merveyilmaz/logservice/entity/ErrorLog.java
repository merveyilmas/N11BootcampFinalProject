package com.merveyilmaz.logservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ErrorLog {

    @Id
    @GeneratedValue(generator = "ErrorLog", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ErrorLog", sequenceName = "ERROR_LOG_ID_SEQ")
    private Long id;

    private LocalDateTime date;
    private String message;
    private String description;
}
