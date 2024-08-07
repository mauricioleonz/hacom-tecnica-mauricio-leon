package com.example.hacommauricioleon.Repositories;

import com.example.hacommauricioleon.Entities.TraceMsg;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.OffsetDateTime;

public interface TraceMsgRepository extends ReactiveMongoRepository<TraceMsg, String> {
    Flux<TraceMsg> findAllByTsBetween(OffsetDateTime from, OffsetDateTime to);

}