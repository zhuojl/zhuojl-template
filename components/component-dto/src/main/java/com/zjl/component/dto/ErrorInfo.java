package com.zjl.component.dto;

public class ErrorInfo {

    private String errCode;

    private String errMessage;

    /**
     * 标记异常系统
     */
    private String errSys;

    private Object errDetail;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrSys() {
        return errSys;
    }

    public void setErrSys(String errSys) {
        this.errSys = errSys;
    }

    public Object getErrDetail() {
        return errDetail;
    }

    public void setErrDetail(Object errDetail) {
        this.errDetail = errDetail;
    }
}
