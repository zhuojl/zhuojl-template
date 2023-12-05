package com.zjl.component.common.exception;

/**
 * @ Description   :  异常工厂实现
 * @ Author        :  da.xue
 * @ CreateDate    :  2020/04/11
 * @ Version       :  1.0
 */
public class ExceptionFactory {

    public static BadRequestException badRequestException(IError error) {
        return new BadRequestException(error);
    }

    public static BadRequestException badRequestException(IError error, Throwable throwable) {
        return new BadRequestException(error, throwable);
    }

    public static SysException sysException(IError error) {
        return new SysException(error);
    }

    public static SysException sysException(IError error, Throwable throwable) {
        return new SysException(error, throwable);
    }


    public static ForbiddenException forbiddenException(IError error) {
        return new ForbiddenException(error);
    }

    public static ForbiddenException forbiddenException(IError error, Throwable throwable) {
        return new ForbiddenException(error, throwable);
    }


    public static NotFoundException notFoundException(IError error) {
        return new NotFoundException(error);
    }

    public static NotFoundException notFoundException(IError error, Throwable throwable) {
        return new NotFoundException(error, throwable);
    }


    public static PermissionDeniedException permissionDeniedException(IError error) {
        return new PermissionDeniedException(error);
    }

    public static PermissionDeniedException permissionDeniedException(IError error, Throwable throwable) {
        return new PermissionDeniedException(error, throwable);
    }


    public static UnauthorizedException unauthorizedException(IError error) {
        return new UnauthorizedException(error);
    }

    public static UnauthorizedException unauthorizedException(IError error, Throwable throwable) {
        return new UnauthorizedException(error, throwable);
    }


    public static BizException bizException(IError error) {
        return new BizException(error);
    }

    public static BizException bizException(IError error, Throwable throwable) {
        return new BizException(error, throwable);
    }

}
