package com.zjl.archetype.web.infra.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventPublisher {

    public void publish(Object obj) {
        log.info("sth:{}", obj);
    }
}
