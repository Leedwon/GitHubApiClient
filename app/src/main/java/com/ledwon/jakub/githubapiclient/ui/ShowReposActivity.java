package com.ledwon.jakub.githubapiclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;
import com.ledwon.jakub.githubapiclient.viewmodels.GitHubViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import static com.ledwon.jakub.githubapiclient.ui.RepoDetailsActivity.REPO_DETAILS_BUNDLE_KEY_REPONAME;

public class ShowReposActivity extends AppCompatActivity {
    private static final String TAG = "ShowReposActivity";

    public static final String SHOW_REPOS_BUNDLE_KEY_USERNAME = "com.ledwon.jakub.githubapiclient.ui.USERNAME";

    private GitHubViewModel mViewModel;
    private Context mContext;

    private ShowReposFragment mShowReposFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        mContext = this;

        mViewModel = ViewModelProviders.of(this).get(GitHubViewModel.class);

        mShowReposFragment = ShowReposFragment.newInstance();

        Intent intent = getIntent();
        final String username = intent.getExtras().getString(SHOW_REPOS_BUNDLE_KEY_USERNAME);

        getSupportActionBar().setTitle(username + "\'s repos");

        final ReposAdapter adapter = new ReposAdapter();
        adapter.setOnItemClickListener(new ReposAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Repo repo) {
                Intent intent = new Intent(mContext, RepoDetailsActivity.class);
                intent.putExtra(SHOW_REPOS_BUNDLE_KEY_USERNAME, username);
                intent.putExtra(REPO_DETAILS_BUNDLE_KEY_REPONAME, repo.getName());
                startActivity(intent);
            }
        });

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_repos_fragment_container, DataLoadingFragment.newInstance()).commit();

        mViewModel.getListRepos(username).observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(List<Repo> repos) {
                if (repos != null) {
                    fragmentManager.beginTransaction().replace(R.id.activity_repos_fragment_container, mShowReposFragment).commit();
                    mShowReposFragment.submitReposList(repos);
                    Log.d(TAG, "onChanged: repos loaded successfully");
                } else {
                    Log.d(TAG, "onChanged: user doesn't exist");
                    Toast.makeText(mContext, "this user doesn't exist", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
