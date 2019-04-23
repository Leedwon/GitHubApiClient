package com.ledwon.jakub.githubapiclient;

import android.content.Context;
import android.content.Intent;

import com.ledwon.jakub.githubapiclient.ui.RepoDetailsActivity;
import com.ledwon.jakub.githubapiclient.ui.ShowReposActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ledwon.jakub.githubapiclient.utils.WaitForViewUtils.waitForViewWithId;

/*
    at this moment a VALID_USERNAME is needed for this test cases to pass but tested user may change its username that's why
    TODO:: inject some mocked response when getListOfRepos(username) is called
*/
@RunWith(AndroidJUnit4.class)
public class ShowReposActivityTest {
    // provide valid github's username so we can check behaviours when repos are downloaded correctly
    private final static String VALID_USERNAME = "leedwon";

    @Rule
    public ActivityTestRule<ShowReposActivity> showReposActivityActivityTestRule = new ActivityTestRule<ShowReposActivity>(ShowReposActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(context, ShowReposActivity.class);
            result.putExtra(ShowReposActivity.SHOW_REPOS_BUNDLE_KEY_USERNAME, VALID_USERNAME);
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
    public void checkDisplayOfShowReposFragment(){
        int waitingTime = 5000; //5s
        int recyclerViewId = R.id.recycler_view_repos;

        onView(isRoot()).perform(waitForViewWithId(recyclerViewId, waitingTime));
        onView(withId(recyclerViewId)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfClickOnRepoStartsRepoDetails(){
        Intents.init();

        int waitingTime = 5000; //5s
        int recyclerViewId = R.id.recycler_view_repos;

        onView(isRoot()).perform(waitForViewWithId(recyclerViewId, waitingTime));
        onView(withId(recyclerViewId)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(hasComponent(RepoDetailsActivity.class.getName()));

        Intents.release();
    }
}
