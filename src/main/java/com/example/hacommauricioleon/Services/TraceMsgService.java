package com.example.hacommauricioleon.Services;

import com.example.hacommauricioleon.Repositories.TraceMsgRepository;
import com.example.hacommauricioleon.Entities.TraceMsg;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TraceMsgService {
    private final TraceMsgRepository traceMsgRepository;
    private final MeterRegistry meterRegistry;
    private Counter insertCounter;

    @PostConstruct
    public void init() {
        insertCounter = meterRegistry.counter("hacom.test.developer.insert.rx");
    }

    public Mono<TraceMsg> saveTraceMsg(TraceMsg traceMsg) {
        return traceMsgRepository.save(traceMsg)
                .doOnSuccess(msg -> {
                    insertCounter.increment();
                });
    }
}
