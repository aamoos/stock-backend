package com.stock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
@Slf4j
public class RestApiController {

    private final WebClient webClient = WebClient.create();

    @Value("${stock.yahoo.url}")
    private String yahooBaseUrl;

    @Value("${stock.yahoo.chart-url}")
    private String yahooChartUrl;

    @Value("${financialmodelingprep.base-url}")
    private String fmpBaseUrl;

    @Value("${financialmodelingprep.apikey}")
    private String fmpApiKey;

    //인기
    @GetMapping("/top")
    public ResponseEntity<String> getTopStocks(
            @RequestParam(name = "start", defaultValue = "0") int start,
            @RequestParam(name = "count", defaultValue = "100") int count
    ) {
        String fullUrl = String.format("%s&count=%d&start=%d", yahooBaseUrl, count, start);

        String response = webClient.get()
                .uri(fullUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    //상세 차트
    @GetMapping("/{symbol}/chart")
    public ResponseEntity<String> getStockChart(
            @PathVariable(name = "symbol") String symbol,
            @RequestParam(name = "range", defaultValue = "1mo") String range,
            @RequestParam(name = "interval", defaultValue = "1d") String interval
    ) {
        log.info("range={}, interval={}", range, interval);
        String url = String.format("%s/%s?range=%s&interval=%s", yahooChartUrl, symbol, range, interval);

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{symbol}/income-statement")
    public ResponseEntity<String> getIncomeStatement(@PathVariable(name = "symbol") String symbol) {
        String url = String.format(
                "%s/income-statement/%s?apikey=%s",
                fmpBaseUrl, symbol, fmpApiKey);

        log.info("FMP Income Statement URL: {}", url);

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }
}
