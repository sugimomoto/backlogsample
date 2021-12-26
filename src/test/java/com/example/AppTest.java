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
import com.nulabinc.backlog4j.conf.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private String apikey;
    private String spaceId;

    public AppTest(){
        Path path = Paths.get("backlog.properties");

        try(BufferedReader reader = Files.newBufferedReader(path,StandardCharsets.UTF_8)){
            Properties properties = new Properties();
            properties.load(reader);

            apikey = properties.getProperty("apikey");
            spaceId = properties.getProperty("spaceid");


        }catch(IOException ex){
            System.out.println(ex);
        }
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
        BacklogConfigure configure = new BacklogComConfigure(spaceId).apiKey(apikey);

        BacklogClient client = new BacklogClientFactory(configure).newClient();
        
        ResponseList<Project> projects = client.getProjects();

        assertEquals(1, projects.size());
        assertEquals("apiupdatecheck", projects.get(0).getName());

    }
}
