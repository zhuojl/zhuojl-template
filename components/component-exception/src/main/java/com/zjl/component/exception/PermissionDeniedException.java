package com.zjl.component.exception;

/**
 * 无权限操作
 * 状态 403
 *
 */
public class PermissionDeniedException extends BaseException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERR_CODE = "PERMISSION_DENIED";

    public PermissionDeniedException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public PermissionDeniedException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public PermissionDeniedException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }

}
