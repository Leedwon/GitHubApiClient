package com.ledwon.jakub.githubapiclient.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.databinding.ActivityMainBinding;
import com.ledwon.jakub.githubapiclient.repository.GitHubRepository;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(rootView);
        mBinding = DataBindingUtil.bind(rootView);

        mBinding.setIMainActivity(this);

        GitHubRepository repo = new GitHubRepository();
        repo.getRepos("leedwon");
    }

    private boolean validateUsername(){
        if(mBinding.inputLayoutUsername.getEditText().getText().toString().trim().isEmpty()){
            mBinding.inputLayoutUsername.setError(getResources().getString(R.string.edit_text_username_error));
            return false;
        }
        mBinding.inputLayoutUsername.setError(null);
        return true;

    }

    @Override
    public void searchRepos() {
        if(validateUsername()){
            Intent intent = new Intent(this, ShowReposActivity.class);
            String username = mBinding.inputLayoutUsername.getEditText().getText().toString().trim();
            intent.putExtra(ShowReposActivity.BUNDLE_KEY_USERNAME, username);

            startActivity(intent);
        }

    }
}
