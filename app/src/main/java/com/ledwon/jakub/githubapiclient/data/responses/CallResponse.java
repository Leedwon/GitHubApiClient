package com.ledwon.jakub.githubapiclient.data.responses;

/*
    For better error handling every response extends this class
    CallResponse stores response code if response was received
    or throwable when call fails
 */
public abstract class CallResponse {
    private Throwable mThrowable;
    private Integer mResponseCode;

    /*
        @param mResponseCode - Call's HTTP response status code
        constructor to be used when Call succeeds and we want to store response code
    */
    public CallResponse(Integer responseCode) {
        this.mResponseCode = responseCode;
        this.mThrowable = null;
    }

    /*
        @param mThrowable - Call's throwable
        constructor to be used when Call fails and we want to store error
     */
    public CallResponse(Throwable throwable) {
        this.mThrowable = throwable;
        this.mResponseCode = null;
    }

    public boolean hasFailed(){
        return mResponseCode == null;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public Integer getResponseCode() {
        return mResponseCode;
    }
}
