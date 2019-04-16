package com.ledwon.jakub.githubapiclient.data.responses;

import com.ledwon.jakub.githubapiclient.data.model.Repo;

import java.util.List;

/*
    Wrapper around Repo class
    that helps with error handling
*/
public class ListOfReposResponse extends CallResponse {
    private List<Repo> mListOfRepos;

    /*
        @param mResposneCode - Call's HTTP response status code
        constructor to be used when Call succeeds and we want to store response code + data
    */
    public ListOfReposResponse(List<Repo> mListOfRepos, Integer mResponseCode) {
        super(mResponseCode);
        this.mListOfRepos = mListOfRepos;
    }

    /*
        @param mThrowable - Call's throwable
        constructor to be used when Call fails and we want to store error
     */
    public ListOfReposResponse(Throwable mThrowable) {
       super(mThrowable);
       this.mListOfRepos = null;
    }

    public List<Repo> getListOfRepos() {
        return mListOfRepos;
    }
}
