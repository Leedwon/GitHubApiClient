package com.ledwon.jakub.githubapiclient.data.responses;

import com.ledwon.jakub.githubapiclient.data.model.Repo;

/*
    Wrapper around Repo class
    that helps with error handling
*/
public class RepoResponse extends CallResponse {
    private Repo mRepo;

    /*
       @param mResponseCode - Call's HTTP response status code
       constructor to be used when Call succeeds and we want to store response code + data
   */
    public RepoResponse(Repo repo, Integer responseCode) {
        super(responseCode);
        this.mRepo = repo;
    }

    /*
    @param mThrowable - Call's throwable
    constructor to be used when Call fails and we want to store error
    */
    public RepoResponse(Throwable throwable) {
        super(throwable);
        this.mRepo = null;
    }

    public Repo getRepo() {
        return mRepo;
    }
}
