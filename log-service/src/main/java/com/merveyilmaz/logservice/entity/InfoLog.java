package com.merveyilmaz.logservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "infoLogs")
public class InfoLog {

    @Id
    @GeneratedValue(generator = "InfoLog", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "InfoLog", sequenceName = "INFO_LOG_ID_SEQ")
    private Long id;

    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;
}
