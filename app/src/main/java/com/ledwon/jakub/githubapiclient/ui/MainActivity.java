package com.ledwon.jakub.githubapiclient.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dagger.android.AndroidInjection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.databinding.ActivityMainBinding;
import com.ledwon.jakub.githubapiclient.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = getLayoutInflater().inflate(R.layout.activity_main, null);
        mBinding = DataBindingUtil.bind(rootView);
        setContentView(mBinding.getRoot());

        mBinding.setIMainActivity(this);
    }

    private boolean validateUsernameAndSetError(){
        if(mBinding.inputLayoutUsername.getEditText().getText().toString().trim().isEmpty()){
            mBinding.inputLayoutUsername.setError(getResources().getString(R.string.edit_text_username_error));
            return false;
        }
        mBinding.inputLayoutUsername.setError(null);
        return true;
    }

    @Override
    public void searchRepos() {
        if(NetworkUtils.isNetworkAvailable(this)) {
            if (validateUsernameAndSetError()) {
                String username = mBinding.inputLayoutUsername.getEditText().getText().toString().trim();
                Intent intent = new Intent(this, ShowReposActivity.class);
                intent.putExtra(ShowReposActivity.SHOW_REPOS_BUNDLE_KEY_USERNAME, username);
                startActivity(intent);
            }
        } else
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();

    }
}
