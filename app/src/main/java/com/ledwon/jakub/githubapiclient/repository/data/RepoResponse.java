package com.ledwon.jakub.githubapiclient.repository.data;

/*
    Wrapper around Repo class
    that helps with error handling
*/
public class RepoResponse {
    private Repo mRepo;
    private Integer mResponseCode;
    private Throwable mThrowable;

    public RepoResponse(Repo mRepo, Integer mResponseCode) {
        this.mRepo = mRepo;
        this.mResponseCode = mResponseCode;
        this.mThrowable = null;
    }

    public RepoResponse(Throwable mThrowable) {
        this.mThrowable = mThrowable;
        this.mRepo = null;
        this.mResponseCode = null;
    }

    public Repo getRepo() {
        return mRepo;
    }

    public Integer getResponseCode() {
        return mResponseCode;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}
