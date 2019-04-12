package com.ledwon.jakub.githubapiclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.databinding.ActivityReposBinding;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;
import com.ledwon.jakub.githubapiclient.viewmodels.GitHubViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ShowReposActivity extends AppCompatActivity {
    private static final String TAG = "ShowReposActivity";
    
    public static final String BUNDLE_KEY_USERNAME = "com.ledwon.jakub.githubapiclient.ui.USERNAME";

    private GitHubViewModel mViewModel;
    private ActivityReposBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = getLayoutInflater().inflate(R.layout.activity_repos, null);

        setContentView(rootView);
        mBinding = DataBindingUtil.bind(rootView);

        mViewModel = ViewModelProviders.of(this).get(GitHubViewModel.class);

        final ReposAdapter adapter = new ReposAdapter();
        adapter.setOnItemClickListener(new ReposAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Repo repo) {

            }
        });

        mBinding.recyclerViewRepos.setAdapter(adapter);
        mBinding.recyclerViewRepos.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerViewRepos.setHasFixedSize(true);

        Intent intent = getIntent();
        String username = intent.getExtras().getString(BUNDLE_KEY_USERNAME);

        getSupportActionBar().setTitle(username + "\'s repos");

        mViewModel.getRepos(username).observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(List<Repo> repos) {
                adapter.submitList(repos);
                Log.d(TAG, "onChanged: ");
            }
        });


    }
}
