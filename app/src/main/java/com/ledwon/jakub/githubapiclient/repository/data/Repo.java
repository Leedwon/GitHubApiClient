package com.ledwon.jakub.githubapiclient.repository.data;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Repo {
    @SerializedName("name")
    private String mName;

    @SerializedName("owner")
    private RepoOwner mOwner;

    @SerializedName("fork")
    private boolean mFork;

    /*
        Integer instead of int for data binding purposes
     */
    @SerializedName("forks_count")
    private Integer mForksCount;

    /*
        Integer instead of int for data binding purposes
    */
    @SerializedName("watchers_count")
    private Integer mWatchers;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("html_url")
    private String mHtmlUrl;

    @SerializedName("description")
    private String mDescription;

    @NonNull
    @Override
    public String toString() {
        return ("name " + mName + "\nfork " + "\nownerName " + mOwner.toString() + "\nis fork " + mFork + "\nforks count " + mForksCount + "\nwatchers " + mWatchers + "\nlanguage " + mLanguage + "\nlink " + mHtmlUrl + "\ndescription " + mDescription);
    }

    public String getName() {
        return mName;
    }

    public RepoOwner getOwner() {
        return mOwner;
    }

    public boolean isFork() {
        return mFork;
    }

    public Integer getForksCount() {
        return mForksCount;
    }

    public Integer getWatchers() {
        return mWatchers;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public String getDescription() {
        return mDescription;
    }

}
