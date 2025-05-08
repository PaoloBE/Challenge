package com.tenpo.challenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.clases.CalcRequest;
import com.tenpo.challenge.client.ExternalClient;
import com.tenpo.challenge.entity.LogMetric;
import com.tenpo.challenge.repository.LogMetricRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class CalcService {
    private final LogMetricRepository repository;
    private final ExternalClient client;
    ObjectMapper mapper = new ObjectMapper();

    public CalcService(LogMetricRepository repository, ExternalClient client) {
        this.repository = repository;
        this.client = client;
    }

    public Double calcFinalNumber(CalcRequest request) {
        try {
            Integer externalNumber = this.client.callNumber().getNumber();
            if (externalNumber == 0) {
                sendMetricError(request, "Error servicio externo");
                return 0.0;
            }
            Integer sum = request.getNum1() + request.getNum2();
            Double calcResult = sum.doubleValue() * ( externalNumber *  sum.doubleValue() / 100);
            sendMetric(request, calcResult);
            return calcResult;
        } catch (Exception e) {
            sendMetricError(request, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<LogMetric> allMetrics() {
        return this.repository.findAll();
    }

    private String requestString(CalcRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"num1\":");
        sb.append(request.getNum1());
        sb.append(",\"num2\":");
        sb.append(request.getNum2());
        sb.append("}");
        return sb.toString();
    }

    @Async
    private void sendMetric(CalcRequest request, Double sum) {
        LogMetric newEntry = new LogMetric(Instant.now(), "api/calculate", requestString(request), sum.toString(), null);
        repository.save(newEntry);
    }

    @Async
    private void sendMetricError(CalcRequest request, String errorMsg){
        LogMetric newEntry = new LogMetric(Instant.now(), "api/calculate", requestString(request), null, errorMsg);
        repository.save(newEntry);
    }
}
