package com.ledwon.jakub.githubapiclient.data.responses;

import com.ledwon.jakub.githubapiclient.data.model.Repo;

/*
    Wrapper around Repo class
    that helps with error handling
*/
public class RepoResponse extends CallResponse {
    private Repo mRepo;


    public RepoResponse(Repo mRepo, Integer mResponseCode) {
        super(mResponseCode);
        this.mRepo = mRepo;
    }

    public RepoResponse(Throwable mThrowable) {
        super(mThrowable);
        this.mRepo = null;
    }

    public Repo getRepo() {
        return mRepo;
    }
}
