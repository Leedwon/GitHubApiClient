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

    @SerializedName("forks_count")
    private int mForksCount;

    @SerializedName("watchers_count")
    private int mWatchers;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("html_url")
    private String mHtmlUrl;

    @SerializedName("description")
    private String mDescription;

    @NonNull
    @Override
    public String toString() {
        return ("name " + mName + "\n"
                + "owner " + mOwner.toString() + "\n"
                + "is fork " + mFork + "\n"
                + "forks count " + mForksCount + "\n"
                + "watchers " + mWatchers + "\n"
                + "language " + mLanguage + "\n"
                + "link " + mHtmlUrl + "\n"
                + "description " + mDescription);
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

    public int getForksCount() {
        return mForksCount;
    }

    public int getWatchers() {
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
