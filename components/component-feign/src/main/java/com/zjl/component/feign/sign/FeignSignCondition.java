package com.zjl.component.feign.sign;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

public class FeignSignCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String displayName = ((AnnotationConfigApplicationContext) context.getResourceLoader()).getDisplayName();
        String contextId = displayName.replace(FeignContext.class.getSimpleName() + "-", "");

        Boolean ignoreSign = context.getEnvironment().getProperty(key(contextId), Boolean.class);
        if (Objects.nonNull(ignoreSign)) {
            return Boolean.TRUE.equals(ignoreSign) ? ConditionOutcome.noMatch("no sign for " + displayName) : ConditionOutcome.match();
        }
        // if null use default
        ignoreSign = context.getEnvironment().getProperty(key("default"), Boolean.class);
        return Boolean.TRUE.equals(ignoreSign) ? ConditionOutcome.noMatch("no sign for " + displayName) : ConditionOutcome.match();

    }

    private String key( String contextId) {
        return contextId + ".feign.client.ignoreSign";

    }


}
