package javaapplication1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.lang.Exception;

public class HTTPRequests 
{
    private URL url;
    private HttpURLConnection connection;
    
    // sends one of the following
    // address 1234 Blabla 
    // name Building Name
    // id 1234567
    
    // Receive one json file
    public Object SendRequest(String parameters) throws MalformedURLException, IOException
    {
        url = new URL("https://webhook.site/d7282a62-09eb-4263-8461-896b21096966");
        connection = (HttpURLConnection) url.openConnection();     
        connection.setRequestMethod("GET");   
        connection.setDoOutput(true); 
        
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
    
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        
        outputStream.writeBytes(parameters);
        outputStream.flush();
        outputStream.close();

        int status = connection.getResponseCode();
        
        Reader streamReader = null;
        
        if (status > 299) 
        {
            streamReader = new InputStreamReader(connection.getErrorStream());
        }
        else 
        {
            streamReader = new InputStreamReader(connection.getInputStream());
        }
        
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
        String inputLine;
        StringBuffer content = new StringBuffer();

        while((inputLine = input.readLine()) != null)
        {
            content.append(inputLine);
        }
        
        input.close();
        connection.disconnect();   
        
        return content;
    }    
}