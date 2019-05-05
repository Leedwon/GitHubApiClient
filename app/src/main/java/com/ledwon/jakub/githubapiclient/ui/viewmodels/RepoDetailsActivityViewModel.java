package com.ledwon.jakub.githubapiclient.ui.viewmodels;

import com.ledwon.jakub.githubapiclient.data.GitHubRepository;
import com.ledwon.jakub.githubapiclient.data.model.Repo;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RepoDetailsActivityViewModel extends ViewModel {

    private GitHubRepository mGitHubRepository;

    private CompositeDisposable mDisposable;

    private MutableLiveData<Repo> mRepo = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();

    @Inject
    public RepoDetailsActivityViewModel(GitHubRepository gitHubRepository) {
        this.mGitHubRepository = gitHubRepository;
        mDisposable = new CompositeDisposable();
    }

    public LiveData<Repo> getRepo() {
        return mRepo;
    }

    public LiveData<String> getError() {
        return mError;
    }

    public void fetchRepo(String username, String repoName) {
        mDisposable.add(mGitHubRepository.getRepo(username, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Repo>() {
                    @Override
                    public void onSuccess(Repo repo) {
                        mRepo.setValue(repo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mError.setValue(e.getMessage());
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
