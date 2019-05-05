package com.ledwon.jakub.githubapiclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.di.ViewModelFactory;
import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.utils.NetworkUtils;
import com.ledwon.jakub.githubapiclient.ui.viewmodels.ShowReposActivityViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;


import static com.ledwon.jakub.githubapiclient.ui.RepoDetailsActivity.REPO_DETAILS_BUNDLE_KEY_REPONAME;

public class ShowReposActivity extends AppCompatActivity {

    public static final String SHOW_REPOS_BUNDLE_KEY_USERNAME = "com.ledwon.jakub.githubapiclient.ui.USERNAME";

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    ShowReposFragment showReposFragment;

    @Inject
    DataLoadingFragment dataLoadingFragment;

    @Inject
    Context context;

    private ShowReposActivityViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_repos);

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowReposActivityViewModel.class);

        Intent intent = getIntent();
        final String username = intent.getExtras().getString(SHOW_REPOS_BUNDLE_KEY_USERNAME);

        getSupportActionBar().setTitle(username + "\'s repos");

        final ReposAdapter adapter = new ReposAdapter();
        adapter.setOnItemClickListener(new ReposAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Repo repo) {
                Intent intent = new Intent(context, RepoDetailsActivity.class);
                intent.putExtra(SHOW_REPOS_BUNDLE_KEY_USERNAME, username);
                intent.putExtra(REPO_DETAILS_BUNDLE_KEY_REPONAME, repo.getName());
                startActivity(intent);
            }
        });

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_repos_fragment_container, dataLoadingFragment).commit();

        mViewModel.fetchRepos(username);
        mViewModel.getListOfRepos().observe(this, repos -> {
            if (!repos.isEmpty()) {
                fragmentManager.beginTransaction().replace(R.id.activity_repos_fragment_container, showReposFragment).commit();
                showReposFragment.submitReposList(repos);
            } else
                displayToastAndFinish(getResources().getString(R.string.no_repos));
        });

        mViewModel.getUserFound().observe(this, bool -> {
            if (!bool) displayToastAndFinish(getResources().getString(R.string.no_user));
        });
    }

    private void displayToastAndFinish(String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        finish();
    }
}
