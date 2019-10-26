
package javaapplication1;

import java.io.IOException;

public class JavaApplication1 
{    
    public static void main(String[] args) throws IOException 
    {
        HTTPRequests httpRequest = new HTTPRequests();
        JSONParse parser = new JSONParse();
        
        Object jsonFile = httpRequest.SendRequest("waffles");
        
        parser.parse(jsonFile);
    }
}
