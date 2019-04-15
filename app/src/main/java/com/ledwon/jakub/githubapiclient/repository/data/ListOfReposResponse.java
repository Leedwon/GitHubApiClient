package com.ledwon.jakub.githubapiclient.repository.data;

import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import java.util.List;

/*
    Wrapper around Repo class
    that helps with error handling
*/
public class ListOfReposResponse {
    private List<Repo> mListOfRepos;
    private Integer mResponseCode;
    private Throwable mThrowable;

    public ListOfReposResponse(List<Repo> mListOfRepos, Integer mResponseCode) {
        this.mListOfRepos = mListOfRepos;
        this.mResponseCode = mResponseCode;
        this.mThrowable = null;
    }

    public ListOfReposResponse(Throwable mThrowable) {
        this.mThrowable = mThrowable;
        this.mListOfRepos = null;
        this.mResponseCode = null;
    }

    public List<Repo> getListOfRepos() {
        return mListOfRepos;
    }

    public Integer getResponseCode() {
        return mResponseCode;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}
