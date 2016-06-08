package com.cdi.web.utils;

import com.cdi.web.enums.CallStatusEnum;
import com.cdi.web.enums.ErrorCodeEnum;

public class DataWrapper<T>  {
    private CallStatusEnum callStatus;
    private ErrorCodeEnum errorCode;
    private T data;


    public DataWrapper() {
        callStatus = CallStatusEnum.SUCCEED;
        errorCode = ErrorCodeEnum.No_Error;
    }

    public CallStatusEnum getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(CallStatusEnum callStatus) {
        this.callStatus = callStatus;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
        if (!errorCode.equals(ErrorCodeEnum.No_Error)) {
            this.callStatus = CallStatusEnum.FAILED;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }




    @Override
    public String toString() {
        return	"Code:" + this.callStatus + "\n" +
                "Error Code:" + this.errorCode+ "\n";
    }
}
