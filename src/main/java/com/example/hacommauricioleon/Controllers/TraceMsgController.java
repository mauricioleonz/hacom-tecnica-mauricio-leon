package com.example.hacommauricioleon.Controllers;

import com.example.hacommauricioleon.Entities.DateRangeRequest;
import com.example.hacommauricioleon.Entities.TraceMsg;
import com.example.hacommauricioleon.Repositories.TraceMsgRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Log4j2
public class TraceMsgController {

    @Autowired
    private TraceMsgRepository traceMsgRepository;

    private final Counter insertCounter;

    public TraceMsgController(MeterRegistry meterRegistry) {
        this.insertCounter = meterRegistry.counter("hacom.test.developer.insert.rx");
    }

    @PostMapping("/trace")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TraceMsg> createTraceMsg(@RequestBody TraceMsg traceMsg) {
        //Controlador con metodo que introduce el TraceMsg
        log.info("Se ha recibido data de traceMsg: {}", traceMsg);
        insertCounter.increment();
        return traceMsgRepository.save(traceMsg)
                .doOnSuccess(msg -> log.info("Se ha insertado el traceMsg: {}", msg));
    }

    @PostMapping("/trace/search")
    public Flux<TraceMsg> getTraceMsgsByDateRange(@RequestBody DateRangeRequest dateRangeRequest) {
        //Controlador con metodo que devuelve el TraceMsg entre rango de fechas
        return traceMsgRepository.findAllByTsBetween(dateRangeRequest.getFrom(), dateRangeRequest.getTo())
                .doOnNext(msg -> log.info("Se ha traido el traceMsg: {}", msg));
    }
}
