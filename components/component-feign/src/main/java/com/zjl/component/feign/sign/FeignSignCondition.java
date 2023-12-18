package com.zjl.component.feign.sign;

import java.util.Objects;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 通过获取contextId，可以通过配置来动态配置一些bean，从而实现差异化处理。 例如，一些feign需要验签，一些不需要
 */
public class FeignSignCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
            AnnotatedTypeMetadata metadata) {
        String displayName = ((AnnotationConfigApplicationContext) context.getResourceLoader()).getDisplayName();
        String contextId = displayName.replace(FeignContext.class.getSimpleName() + "-", "");

        Boolean ignoreSign = context.getEnvironment().getProperty(key(contextId), Boolean.class);
        if (Objects.nonNull(ignoreSign)) {
            return Boolean.TRUE.equals(ignoreSign) ? ConditionOutcome.noMatch(
                    "no sign for " + displayName) : ConditionOutcome.match();
        }
        // if null use default
        ignoreSign = context.getEnvironment().getProperty(key("default"), Boolean.class);
        return Boolean.TRUE.equals(ignoreSign) ? ConditionOutcome.match()
                : ConditionOutcome.noMatch("no sign for " + displayName);

    }

    private String key(String contextId) {
        return contextId + ".feign.client.ignoreSign";

    }


}
