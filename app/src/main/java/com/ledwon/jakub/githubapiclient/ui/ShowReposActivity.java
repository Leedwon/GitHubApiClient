package com.ledwon.jakub.githubapiclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.di.ViewModelFactory;
import com.ledwon.jakub.githubapiclient.data.responses.ListOfReposResponse;
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

    private ShowReposActivityViewModel mViewModel;

    Context context;
    ShowReposFragment showReposFragment;
    DataLoadingFragment dataLoadingFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_repos);

        context = this;
        showReposFragment = ShowReposFragment.newInstance();
        dataLoadingFragment = DataLoadingFragment.newInstance();

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

        mViewModel.getListOfRepos(username).observe(this, new Observer<ListOfReposResponse>() {
            @Override
            public void onChanged(ListOfReposResponse listOfReposResponse) {
                if (listOfReposResponse != null) {
                    List<Repo> repos = listOfReposResponse.getListOfRepos();
                    Integer responseCode = listOfReposResponse.getResponseCode();
                    Throwable throwable = listOfReposResponse.getThrowable();

                    if (responseCode != null) {
                        if (responseCode == NetworkUtils.HTTP_OK) {
                            if (repos.size() > 0) {
                                fragmentManager.beginTransaction().replace(R.id.activity_repos_fragment_container, showReposFragment).commit();
                                showReposFragment.submitReposList(listOfReposResponse.getListOfRepos());
                            } else {
                                Toast.makeText(context, getResources().getString(R.string.no_repos), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else if (responseCode == NetworkUtils.HTTP_NOT_FOUND) {
                            Toast.makeText(context, getResources().getString(R.string.no_user), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(context, getResources().getString(R.string.server_response_error), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else if (listOfReposResponse.getThrowable() != null) {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(context, getResources().getString(R.string.sth_went_wrong), Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}
