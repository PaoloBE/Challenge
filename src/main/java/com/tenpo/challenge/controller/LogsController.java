package com.tenpo.challenge.controller;

import com.tenpo.challenge.entity.LogMetric;
import com.tenpo.challenge.service.CalcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/logs")
public class LogsController {
    private final CalcService service;

    public LogsController(CalcService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<LogMetric> returnLogsAll() {
        return this.service.allMetrics();
    }
}
