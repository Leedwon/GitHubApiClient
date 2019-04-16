package com.ledwon.jakub.githubapiclient.data.responses;

import com.ledwon.jakub.githubapiclient.data.model.Repo;

import java.util.List;

/*
    Wrapper around Repo class
    that helps with error handling
*/
public class ListOfReposResponse extends CallResponse {
    private List<Repo> mListOfRepos;

    public ListOfReposResponse(List<Repo> mListOfRepos, Integer mResponseCode) {
        super(mResponseCode);
        this.mListOfRepos = mListOfRepos;
    }

    public ListOfReposResponse(Throwable mThrowable) {
       super(mThrowable);
       this.mListOfRepos = null;
    }

    public List<Repo> getListOfRepos() {
        return mListOfRepos;
    }
}
