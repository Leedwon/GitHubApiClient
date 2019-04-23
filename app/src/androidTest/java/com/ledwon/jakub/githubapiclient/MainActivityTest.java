package com.ledwon.jakub.githubapiclient;

import android.content.Context;

import com.ledwon.jakub.githubapiclient.ui.MainActivity;
import com.ledwon.jakub.githubapiclient.ui.ShowReposActivity;
import com.ledwon.jakub.githubapiclient.utils.NetworkUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ledwon.jakub.githubapiclient.utils.EspressoTextInputLayoutUtils.onEditTextWithinTilWithId;
import static com.ledwon.jakub.githubapiclient.utils.EspressoTextInputLayoutUtils.onErrorViewWithinTilWithId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initIntents(){
        Intents.init();
    }

    @After
    public void releaseIntents(){
        Intents.release();
    }

    @Test
    public void checkViewDisplay(){
        onView(withId(R.id.input_layout_username)).check(matches(isDisplayed()));
        onView(withText(R.string.button_search_repos)).check(matches(isDisplayed()));
    }

    @Test
    public void checkErrorOnWrongInput(){
        String input = "";

        Context context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();
        String expectedError = context.getResources().getString(R.string.edit_text_username_error);

        // type wrong input
        onEditTextWithinTilWithId(R.id.input_layout_username).perform(typeText(input));

        // click on search repo's button
        onView(withText(R.string.button_search_repos)).perform(click());

        onErrorViewWithinTilWithId(R.id.input_layout_username).check(matches(withText(expectedError)));
    }

    @Test
    public void checkLaunchOfShowReposActivity(){
        String validInput = "temp";

        onEditTextWithinTilWithId(R.id.input_layout_username).perform(typeText(validInput));
        onView(withText(R.string.button_search_repos)).perform(click());

        intended(hasComponent(ShowReposActivity.class.getName()));
    }

    @Test
    public void checkIntentExtra(){
        String validInput = "temp";

        onEditTextWithinTilWithId(R.id.input_layout_username).perform(typeText(validInput));
        onView(withText(R.string.button_search_repos)).perform(click());

        intended(allOf(hasExtra(ShowReposActivity.SHOW_REPOS_BUNDLE_KEY_USERNAME, validInput)));
    }

    /*
        to test this behaviour disable internet connection on the device
        I'm not doing it programmatically as it would require unnecessary permissions
     */
    @Test
    public void checkNoInternetError(){
        Context context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();
        if(!NetworkUtils.isNetworkAvailable(context)){
            String validUsername = "temp";
            onEditTextWithinTilWithId(R.id.input_layout_username).perform(typeText(validUsername));

            onView(withText(R.string.button_search_repos)).perform(click());

            onView(withText(R.string.no_internet)).
                    inRoot(withDecorView(not(is(mainActivityTestRule.getActivity().getWindow().getDecorView())))).
                    check(matches(isDisplayed()));
        }
    }
}
