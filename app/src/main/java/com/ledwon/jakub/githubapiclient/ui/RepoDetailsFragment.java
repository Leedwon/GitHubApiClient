package com.ledwon.jakub.githubapiclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.databinding.FragmentRepoDetailsBinding;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;
import com.ledwon.jakub.githubapiclient.utils.RepoJsonConverter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class RepoDetailsFragment extends Fragment {
    public static final String REPO_DETAILS_BUNDLE_KEY_REPO = "com.ledwon.jakub.githubapiclient.ui.REPO";

    private FragmentRepoDetailsBinding mBinding;

    public static RepoDetailsFragment newInstance() {return new RepoDetailsFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repo_details, null);

        Bundle bundle = getArguments();
        Repo repo = RepoJsonConverter.toRepo(bundle.getString(REPO_DETAILS_BUNDLE_KEY_REPO));

        mBinding = DataBindingUtil.bind(rootView);

        mBinding.setRepo(repo);
        mBinding.setNoDescriptionString(getString(R.string.no_description));

        return rootView;
    }
}
