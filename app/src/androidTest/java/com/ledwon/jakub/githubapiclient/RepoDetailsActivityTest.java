package com.ledwon.jakub.githubapiclient;

import android.content.Context;
import android.content.Intent;

import com.ledwon.jakub.githubapiclient.ui.RepoDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ledwon.jakub.githubapiclient.utils.WaitForViewUtils.waitForViewWithId;

/*
    at this moment a VALID_USERNAME and VALID_REPO is needed for this test cases to pass but tested user may change its username or delete repo that's why
    TODO:: inject some mocked response when getRepo(username, repo) is called
*/
@RunWith(AndroidJUnit4.class)
public class RepoDetailsActivityTest {
    private static final String VALID_USERNAME = "leedwon";
    private static final String VALID_REPO = "GitHubApiClient";

    @Rule
    public ActivityTestRule<RepoDetailsActivity> showReposActivityActivityTestRule = new ActivityTestRule<RepoDetailsActivity>(RepoDetailsActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(context, RepoDetailsActivity.class);
            result.putExtra(RepoDetailsActivity.REPO_DETAILS_BUNDLE_KEY_USERNAME, VALID_USERNAME);
            result.putExtra(RepoDetailsActivity.REPO_DETAILS_BUNDLE_KEY_REPONAME, VALID_REPO);
            return result;
        }
    };

    @Test
    public void checkDisplayOfDataLoadingFragment(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String dataLoading = context.getResources().getString(R.string.data_loading);

        onView(withText(dataLoading)).check(matches(isDisplayed()));
    }

    @Test
    public void checkDisplayOfRepoDetailsFragment(){
        int waitingTime = 5000; //5s
        int id = R.id.text_view_repo_details_repo_name;

        onView(isRoot()).perform(waitForViewWithId(id, waitingTime));
        onView(withId(id)).check(matches(isDisplayed()));
    }



}
