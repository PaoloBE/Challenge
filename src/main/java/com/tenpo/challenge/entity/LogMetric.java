package com.tenpo.challenge.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "log_metric")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant instant;
    private String endpoint;
    private String params;
    private String response;
    private String error;

    public LogMetric(Instant instant, String endpoint, String params, String response, String error) {
        this.instant = instant;
        this.endpoint = endpoint;
        this.params = params;
        this.response = response;
        this.error = error;
    }
}
