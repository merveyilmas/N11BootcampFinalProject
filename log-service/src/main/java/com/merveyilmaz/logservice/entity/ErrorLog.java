package com.merveyilmaz.logservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "errorLogs")
public class ErrorLog {

    @Id
    @GeneratedValue(generator = "ErrorLog", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ErrorLog", sequenceName = "ERROR_LOG_ID_SEQ")
    private Long id;

    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;
}
