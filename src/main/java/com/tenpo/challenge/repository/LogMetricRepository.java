package com.tenpo.challenge.repository;

import com.tenpo.challenge.entity.LogMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogMetricRepository extends JpaRepository<LogMetric, Integer> {
    List<LogMetric> findAll();
}
