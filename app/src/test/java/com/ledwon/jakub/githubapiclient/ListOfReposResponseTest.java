package com.ledwon.jakub.githubapiclient;

import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.data.responses.ListOfReposResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ListOfReposResponseTest {

    @Test
    public void success(){
        ArrayList<Repo> repos = new ArrayList<>();
        int http_ok = 200;
        ListOfReposResponse response = new ListOfReposResponse(repos, http_ok);
        assertFalse(response.hasFailed());
    }

    @Test
    public void fail(){
        Throwable throwable = new Throwable("error_message");
        ListOfReposResponse response = new ListOfReposResponse(throwable);
        assertTrue(response.hasFailed());
    }
}
