package com.ledwon.jakub.githubapiclient.data.responses;

/*
    For better error handling every response extends this class
    CallResponse can store response code if response was received
    or it can fail to execute and in that case it stores throwable
    and throwable if appeared otherwise it's a null
 */
public class CallResponse {
    private Throwable mThrowable;
    private Integer mResponseCode;

    public CallResponse(Integer mResponseCode) {
        this.mResponseCode = mResponseCode;
        this.mThrowable = null;
    }

    public CallResponse(Throwable mTrowable) {
        this.mThrowable = mTrowable;
        this.mResponseCode = null;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public Integer getResponseCode() {
        return mResponseCode;
    }
}
