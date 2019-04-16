package com.ledwon.jakub.githubapiclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.databinding.FragmentReposBinding;
import com.ledwon.jakub.githubapiclient.data.model.Repo;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ShowReposFragment extends Fragment {
    private FragmentReposBinding mBinding;
    private ReposAdapter mAdapter;

    public static ShowReposFragment newInstance() {return  new ShowReposFragment();}

    /*
        Most often we shouldn't override Fragment's constructor
        especially if we want to pass some arguments to fragment, because when android decides to recreate Fragment it will use no argument constructor
        that's why we are using newInstance() where we can .setArguments(Bundle bundle) to our fragment.
        But here we are not passing any arguments, and we need our mAdapter to be created before we submitReposList.

        The other solution would be to pass Array Of Repos as JSON object via arguments to this fragment but this creates a lot of boilerplate code
        as we can see in RepoDetailsActivity & RepoDetailsFragment so I've chosen other approach here.
     */
    @Inject
    public ShowReposFragment() {
        mAdapter = new ReposAdapter();

        mAdapter.setOnItemClickListener(new ReposAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Repo repo) {
                Intent intent = new Intent(getActivity(), RepoDetailsActivity.class);
                intent.putExtra(RepoDetailsActivity.REPO_DETAILS_BUNDLE_KEY_USERNAME, repo.getOwner().getLogin());
                intent.putExtra(RepoDetailsActivity.REPO_DETAILS_BUNDLE_KEY_REPONAME, repo.getName());
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repos, null);
        mBinding = DataBindingUtil.bind(rootView);

        mBinding.recyclerViewRepos.setAdapter(mAdapter);
        mBinding.recyclerViewRepos.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerViewRepos.setHasFixedSize(true);

        return mBinding.getRoot();
    }

    public void submitReposList(List<Repo> repos){
        mAdapter.submitList(repos);
    }
}
