package com.ledwon.jakub.githubapiclient;

import android.content.Context;
import android.widget.EditText;

import com.ledwon.jakub.githubapiclient.ui.MainActivity;
import com.ledwon.jakub.githubapiclient.utils.NetworkUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.action.TypeTextAction;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.ledwon.jakub.githubapiclient.EspressoTextInputLayoutUtils.onEditTextWithinTilWithId;
import static com.ledwon.jakub.githubapiclient.EspressoTextInputLayoutUtils.onErrorViewWithinTilWithId;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

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

    // to test this behaviour disable internet connection on the device
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
