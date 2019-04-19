package com.ledwon.jakub.githubapiclient;

import com.ledwon.jakub.githubapiclient.data.GitHubApi;
import com.ledwon.jakub.githubapiclient.data.GitHubRepository;
import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.data.responses.ListOfReposResponse;
import com.ledwon.jakub.githubapiclient.data.responses.RepoResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import okhttp3.internal.http.RealResponseBody;
import retrofit2.Response;
import retrofit2.mock.Calls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GitHubRepositoryTest {
    private static final String USERNAME = "username";
    private static final String REPO = "repo";

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    GitHubApi gitHubApi;

    @InjectMocks
    GitHubRepository repository;

    private ArrayList<Repo> mockListOfRepos(int noRepos){
        ArrayList<Repo> repos = new ArrayList<>();

        for(int i = 0; i < noRepos; ++i){
            repos.add(new Repo());
        }
        return repos;
    }

    @Test
    public void receiveSuccessCall(){
        int codeThatShouldBeReceived = 200;
        when(gitHubApi.getRepo(USERNAME, REPO)).thenReturn(retrofit2.mock.Calls.response(new Repo()));

        RepoResponse response = repository.getRepo(USERNAME, REPO).getValue();

        assertFalse(response.hasFailed());
        assertEquals(codeThatShouldBeReceived, (int)response.getResponseCode());
    }

    @Test
    public void receiveFailedCall(){
        String errorMsg = "error";
        when(gitHubApi.getRepo(USERNAME, REPO)).thenReturn(retrofit2.mock.Calls.<Repo>failure(new Throwable(errorMsg)));

        RepoResponse response = repository.getRepo(USERNAME, REPO).getValue();

        assertTrue(response.hasFailed());
        assertEquals(errorMsg, response.getThrowable().getMessage());
    }

    @Test
    public void testNoUserCase(){
        int http_not_found = 404;
        Response<List<Repo>> mockResponse = Response.error(http_not_found, new RealResponseBody(null, 0, null));
        when(gitHubApi.getListOfRepos(USERNAME)).thenReturn(Calls.response(mockResponse));

        ListOfReposResponse response = repository.getListOfRepos(USERNAME).getValue();

        assertFalse(response.hasFailed());
        assertEquals(http_not_found, (int)response.getResponseCode());
    }

    @Test
    public void loadListOfReposTest(){
        int size = 10;
        ArrayList<Repo> mockedRepos = mockListOfRepos(size);
        when(gitHubApi.getListOfRepos(USERNAME)).thenReturn(retrofit2.mock.Calls.<List<Repo>>response(mockedRepos));

        ListOfReposResponse reposResponse = repository.getListOfRepos(USERNAME).getValue();

        assertEquals(mockedRepos.size(), reposResponse.getListOfRepos().size());
    }
}
