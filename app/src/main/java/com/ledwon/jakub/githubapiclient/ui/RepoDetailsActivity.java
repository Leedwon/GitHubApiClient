package com.ledwon.jakub.githubapiclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;
import com.ledwon.jakub.githubapiclient.utils.RepoJsonConverter;
import com.ledwon.jakub.githubapiclient.viewmodels.GitHubViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RepoDetailsActivity extends AppCompatActivity {
    private static final String TAG = "RepoDetailsActivity";

    public static final String REPO_DETAILS_BUNDLE_KEY_USERNAME = "com.ledwon.jakub.githubapiclient.ui.USERNAME";
    public static final String REPO_DETAILS_BUNDLE_KEY_REPONAME = "com.ledwon.jakub.githubapiclient.ui.REPONAME";

    private GitHubViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_repo_details_fragment_container, DataLoadingFragment.newInstance());

        mViewModel = ViewModelProviders.of(this).get(GitHubViewModel.class);

        Intent intent = getIntent();
        String username = intent.getExtras().getString(REPO_DETAILS_BUNDLE_KEY_USERNAME);
        String repo = intent.getExtras().getString(REPO_DETAILS_BUNDLE_KEY_REPONAME);

        mViewModel.getRepo(username, repo).observe(this, new Observer<Repo>() {
            @Override
            public void onChanged(Repo repo) {
                if (repo != null) {
                    Log.d(TAG, "onChanged: repo received " + repo.toString());

                    RepoDetailsFragment repoDetailsFragment = RepoDetailsFragment.newInstance();
                    repoDetailsFragment.setArguments(createRepoBundle(repo));

                    fragmentManager.beginTransaction().replace(R.id.activity_repo_details_fragment_container, repoDetailsFragment).commit();
                }
                else {
                    Toast.makeText(RepoDetailsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private Bundle createRepoBundle(Repo repo){
        Bundle bundle = new Bundle();
        bundle.putString(RepoDetailsFragment.REPO_DETAILS_BUNDLE_KEY_REPO, RepoJsonConverter.toJsonString(repo));
        return bundle;
    }

}
