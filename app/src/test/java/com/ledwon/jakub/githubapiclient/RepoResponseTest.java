package com.ledwon.jakub.githubapiclient;

import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.data.responses.RepoResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class RepoResponseTest {

    @Test
    public void success(){
        Repo repo = new Repo();
        int http_ok = 200;
        RepoResponse response = new RepoResponse(repo, http_ok);
        assertFalse(response.hasFailed());
    }

    @Test
    public void fail(){
        Throwable throwable = new Throwable("error_message");
        RepoResponse response = new RepoResponse(throwable);
        assertTrue(response.hasFailed());
    }
}
