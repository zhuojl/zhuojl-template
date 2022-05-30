package com.zjl.component.exception;

import java.util.Objects;

/**
 * @ Description   :  异常工厂实现
 * @ Author        :  da.xue
 * @ CreateDate    :  2020/04/11
 * @ Version       :  1.0
 */
public class ExceptionFactory {

    /**
     * 不提供 throwBizException方法，这样在外部程序可快速中断流程
     */
    public static PermissionDeniedException permissionDeniedException(Error error, String extendMsg) {
        String errorMsg = error.errorMsg();
        if (Objects.nonNull(extendMsg) && extendMsg.length() > 0) {
            errorMsg += ": " + extendMsg;
        }
        return new PermissionDeniedException(error.errorCode(), errorMsg);
    }

    public static PermissionDeniedException permissionDeniedException(Error error) {
        return new PermissionDeniedException(error.errorCode(), error.errorMsg());
    }

    public static NotFoundException notFoundRequestException(Error error, String extendMsg) {
        String errorMsg = error.errorMsg();
        if (Objects.nonNull(extendMsg) && extendMsg.length() > 0) {
            errorMsg += ": " + extendMsg;
        }
        return new NotFoundException(error.errorCode(), errorMsg);
    }

    public static NotFoundException notFoundRequestException(Error error) {
        return new NotFoundException(error.errorCode(), error.errorMsg());
    }

    public static BadRequestException badRequestException(Error error, String extendMsg) {
        String errorMsg = error.errorMsg();
        if (Objects.nonNull(extendMsg) && extendMsg.length() > 0) {
            errorMsg += ": " + extendMsg;
        }
        return new BadRequestException(error.errorCode(), errorMsg);
    }

    public static BadRequestException badRequestException(Error error) {
        return new BadRequestException(error.errorCode(), error.errorMsg());
    }

    public static BizException bizException(Error error, String extendMsg) {
        String errorMsg = error.errorMsg();
        if (Objects.nonNull(extendMsg) && extendMsg.length() > 0) {
            errorMsg += ": " + extendMsg;
        }
        return new BizException(error.errorCode(), errorMsg);
    }

    public static BizException bizException(Error error) {
        return new BizException(error.errorCode(), error.errorMsg());
    }

    public static SysException sysException(Error error, String extendMsg) {
        String errorMsg = error.errorMsg();
        if (Objects.nonNull(extendMsg) && extendMsg.length() > 0) {
            errorMsg += ": " + extendMsg;
        }
        return new SysException(error.errorCode(), errorMsg);
    }

    public static SysException sysException(Error error) {
        return new SysException(error.errorCode(), error.errorMsg());
    }

}
