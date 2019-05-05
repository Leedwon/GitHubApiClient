package com.ledwon.jakub.githubapiclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.di.ViewModelFactory;
import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.utils.NetworkUtils;
import com.ledwon.jakub.githubapiclient.utils.RepoJsonConverter;
import com.ledwon.jakub.githubapiclient.ui.viewmodels.RepoDetailsActivityViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;

public class RepoDetailsActivity extends AppCompatActivity {
    public static final String REPO_DETAILS_BUNDLE_KEY_USERNAME = "com.ledwon.jakub.githubapiclient.ui.USERNAME";
    public static final String REPO_DETAILS_BUNDLE_KEY_REPONAME = "com.ledwon.jakub.githubapiclient.ui.REPONAME";

    @Inject
    RepoDetailsFragment repoDetailsFragment;
    @Inject
    DataLoadingFragment dataLoadingFragment;

    @Inject
    ViewModelFactory viewModelFactory;
    private RepoDetailsActivityViewModel mViewModel;

    @Inject
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_repo_details);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.activity_repo_details_fragment_container, dataLoadingFragment).commit();

        if (!NetworkUtils.isNetworkAvailable(context))
            displayToastAndFinish(getResources().getString(R.string.no_internet));

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoDetailsActivityViewModel.class);

        Intent intent = getIntent();
        String username = intent.getExtras().getString(REPO_DETAILS_BUNDLE_KEY_USERNAME);
        String repo = intent.getExtras().getString(REPO_DETAILS_BUNDLE_KEY_REPONAME);

        mViewModel.fetchRepo(username, repo);

        mViewModel.getRepo().observe(this, fetchedRepo -> {
            repoDetailsFragment.setArguments(createRepoBundle(fetchedRepo));
            fragmentManager.beginTransaction().replace(R.id.activity_repo_details_fragment_container, repoDetailsFragment).commit();
        });

        mViewModel.getError().observe(this, this::displayToastAndFinish);
    }

    private Bundle createRepoBundle(Repo repo) {
        Bundle bundle = new Bundle();
        bundle.putString(RepoDetailsFragment.REPO_DETAILS_BUNDLE_KEY_REPO, RepoJsonConverter.toJsonString(repo));
        return bundle;
    }

    private void displayToastAndFinish(String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        finish();
    }

}
