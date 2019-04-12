package com.ledwon.jakub.githubapiclient.repository.data;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Repo {
    @SerializedName("name")
    private String mName;

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
        return ("name " + mName + "\nfork " + mFork + "\nforks count " + mForksCount + "\nwatchers " + mWatchers + "\nlanguage " + mLanguage + "\nlink " + mHtmlUrl+ "\ndescription " + mDescription);
    }

    public String getName() {
        return mName;
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
