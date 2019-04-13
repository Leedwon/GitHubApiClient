package com.ledwon.jakub.githubapiclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.databinding.FragmentReposBinding;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ShowReposFragment extends Fragment {
    private FragmentReposBinding mBinding;
    private ReposAdapter adapter;

    public static ShowReposFragment newInstance() {return  new ShowReposFragment();}

    /*
        Normally we shouldn't override Fragment's constructor
        especially if we want to pass some arguments to fragment because when android decides to recreate Fragment it will use no argument constructor
        that's why we are using newInstance() where we can .setArguments(Bundle bundle) to our fragment.
        But this one is a special case as we are not passing any arguments anyway, and we need our adapter to be created asap
        otherwise adapter would be null when we call submitReposList
        The other solution would be to pass Array Of Repos as JSON object via arguments to this fragment but this creates a lot of boilerplate code
        as we can see in RepoDetailsActivity & RepoDetailsFragment so I decided not to do this
     */
    public ShowReposFragment() {
        adapter = new ReposAdapter();

        adapter.setOnItemClickListener(new ReposAdapter.onItemClickListener() {
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

        mBinding.recyclerViewRepos.setAdapter(adapter);
        mBinding.recyclerViewRepos.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerViewRepos.setHasFixedSize(true);

        return rootView;
    }

    public void submitReposList(List<Repo> repos){
        adapter.submitList(repos);
    }
}
