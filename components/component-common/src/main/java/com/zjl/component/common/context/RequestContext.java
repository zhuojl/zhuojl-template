package com.zjl.component.common.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestContext {

    private String realIp;
    private String traceId;
    private String userId;

}
