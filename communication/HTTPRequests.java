import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;

public class HTTPRequests 
{
    private URL url;
    private HttpURLConnection connection;
    final private String SERVER_ADDRESS = "http://100.79.2.35:5000/";
    
    // Receive json file
    public String SendRequest(String parameters) throws MalformedURLException, IOException
    {
        String dataAddress = SERVER_ADDRESS + parameters;
        url = new URL(dataAddress);
        connection = (HttpURLConnection) url.openConnection();     
        connection.setRequestMethod("GET");   
        //connection.setDoOutput(true); 
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
    
       // DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        
        /*
        outputStream.writeBytes(parameters);
        outputStream.flush();
        outputStream.close();
*/
        int status = connection.getResponseCode();
        Reader streamReader = null;
        
        if (status > 299) 
        {
            streamReader = new InputStreamReader(connection.getErrorStream());
            return null;
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
        
        return content.toString();
    }    
}
