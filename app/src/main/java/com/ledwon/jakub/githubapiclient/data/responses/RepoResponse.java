package com.ledwon.jakub.githubapiclient.data.responses;

import com.ledwon.jakub.githubapiclient.data.model.Repo;

/*
    Wrapper around Repo class
    that helps with error handling
*/
public class RepoResponse extends CallResponse {
    private Repo mRepo;

    /*
       @param mResposneCode - Call's HTTP response status code
       constructor to be used when Call succeeds and we want to store response code + data
   */
    public RepoResponse(Repo mRepo, Integer mResponseCode) {
        super(mResponseCode);
        this.mRepo = mRepo;
    }

    /*
    @param mThrowable - Call's throwable
    constructor to be used when Call fails and we want to store error
    */
    public RepoResponse(Throwable mThrowable) {
        super(mThrowable);
        this.mRepo = null;
    }

    public Repo getRepo() {
        return mRepo;
    }
}
