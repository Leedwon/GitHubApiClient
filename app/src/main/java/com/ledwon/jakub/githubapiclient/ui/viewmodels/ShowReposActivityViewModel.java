package com.ledwon.jakub.githubapiclient.ui.viewmodels;

import android.util.Log;

import com.ledwon.jakub.githubapiclient.data.GitHubRepository;
import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ShowReposActivityViewModel extends ViewModel {
    private static final String TAG = "ShowReposActivityViewMo";

    private GitHubRepository mGitHubRepository;

    private CompositeDisposable mDisposable;

    private MutableLiveData<List<Repo>> mRepos = new MutableLiveData<>();
    private MutableLiveData<Boolean> mUserFound = new MutableLiveData<>();

    @Inject
    public ShowReposActivityViewModel(GitHubRepository gitHubRepository) {
        this.mGitHubRepository = gitHubRepository;
        mDisposable = new CompositeDisposable();
    }

    public LiveData<List<Repo>> getListOfRepos() {
        return mRepos;
    }

    public MutableLiveData<Boolean> getUserFound() {
        return mUserFound;
    }

    public void fetchRepos(String username) {
        mDisposable.add(mGitHubRepository.getListOfRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                    @Override
                    public void onSuccess(List<Repo> repos) {
                        mRepos.setValue(repos);
                        mUserFound.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage().contains(String.valueOf(NetworkUtils.HTTP_NOT_FOUND)))
                            mUserFound.setValue(false);
                        Log.d(TAG, "onError throwable :" + e.getMessage());
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
