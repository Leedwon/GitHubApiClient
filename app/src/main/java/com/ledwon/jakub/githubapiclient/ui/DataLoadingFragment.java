package com.ledwon.jakub.githubapiclient.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ledwon.jakub.githubapiclient.R;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DataLoadingFragment extends Fragment {

    @Inject
    public DataLoadingFragment(){

    }

    public static DataLoadingFragment newInstance() {
        return new DataLoadingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_loading, container, false);
    }
}
