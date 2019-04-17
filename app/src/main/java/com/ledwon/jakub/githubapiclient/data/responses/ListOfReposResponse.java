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
        @param mResponseCode - Call's HTTP response status code
        constructor to be used when Call succeeds and we want to store response code + data
    */
    public ListOfReposResponse(List<Repo> listOfRepos, Integer responseCode) {
        super(responseCode);
        this.mListOfRepos = listOfRepos;
    }

    /*
        @param mThrowable - Call's throwable
        constructor to be used when Call fails and we want to store error
     */
    public ListOfReposResponse(Throwable throwable) {
       super(throwable);
       this.mListOfRepos = null;
    }

    public List<Repo> getListOfRepos() {
        return mListOfRepos;
    }
}
