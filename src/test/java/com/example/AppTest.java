package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.api.option.CreateIssueParams;
import com.nulabinc.backlog4j.conf.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private String apikey;
    private String spaceId;

    BacklogClient client;

    public AppTest() throws MalformedURLException{
        Path path = Paths.get("backlog.properties");

        try(BufferedReader reader = Files.newBufferedReader(path,StandardCharsets.UTF_8)){
            Properties properties = new Properties();
            properties.load(reader);

            apikey = properties.getProperty("apikey");
            spaceId = properties.getProperty("spaceid");

        }catch(IOException ex){
            System.out.println(ex);
        }

        BacklogConfigure configure = new BacklogComConfigure(spaceId).apiKey(apikey);

        client = new BacklogClientFactory(configure).newClient();

    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void getProjectTest() throws MalformedURLException{
        
        ResponseList<Project> projects = client.getProjects();

        assertEquals(1, projects.size());
        assertEquals("apiupdatecheck", projects.get(0).getName());
        
    }

    @Test
    public void createIssueTest(){

        
        CreateIssueParams issue = new CreateIssueParams(245615, "HelloBacklog4J", 1199017, Issue.PriorityType.High);
        Issue result = client.createIssue(issue);

        System.out.println(result.getId());


    }
}
