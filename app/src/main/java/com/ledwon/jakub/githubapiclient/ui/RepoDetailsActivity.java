package com.ledwon.jakub.githubapiclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.di.ViewModelFactory;
import com.ledwon.jakub.githubapiclient.data.responses.RepoResponse;
import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.utils.NetworkUtils;
import com.ledwon.jakub.githubapiclient.utils.RepoJsonConverter;
import com.ledwon.jakub.githubapiclient.ui.viewmodels.RepoDetailsActivityViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;

public class RepoDetailsActivity extends AppCompatActivity {

    public static final String REPO_DETAILS_BUNDLE_KEY_USERNAME = "com.ledwon.jakub.githubapiclient.ui.USERNAME";
    public static final String REPO_DETAILS_BUNDLE_KEY_REPONAME = "com.ledwon.jakub.githubapiclient.ui.REPONAME";

    @Inject
    ViewModelFactory viewModelFactory;

    private RepoDetailsActivityViewModel mViewModel;

    RepoDetailsFragment repoDetailsFragment;
    DataLoadingFragment dataLoadingFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_repo_details);

        repoDetailsFragment = RepoDetailsFragment.newInstance();
        dataLoadingFragment = DataLoadingFragment.newInstance();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_repo_details_fragment_container, dataLoadingFragment);

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoDetailsActivityViewModel.class);

        Intent intent = getIntent();
        String username = intent.getExtras().getString(REPO_DETAILS_BUNDLE_KEY_USERNAME);
        String repo = intent.getExtras().getString(REPO_DETAILS_BUNDLE_KEY_REPONAME);

        mViewModel.getRepo(username, repo).observe(this, new Observer<RepoResponse>() {
            @Override
            public void onChanged(RepoResponse repoResponse) {
                if(repoResponse != null){
                    Repo repo = repoResponse.getRepo();
                    Integer responseCode = repoResponse.getResponseCode();
                    Throwable throwable = repoResponse.getThrowable();

                    if(responseCode != null){
                        if(responseCode == NetworkUtils.HTTP_OK){
                            repoDetailsFragment.setArguments(createRepoBundle(repo));
                            fragmentManager.beginTransaction().replace(R.id.activity_repo_details_fragment_container, repoDetailsFragment).commit();
                        } else {
                            Toast.makeText(RepoDetailsActivity.this, getResources().getString(R.string.server_response_error), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else if(throwable != null){
                        Toast.makeText(RepoDetailsActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(RepoDetailsActivity.this, getResources().getString(R.string.sth_went_wrong), Toast.LENGTH_SHORT).show();
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
