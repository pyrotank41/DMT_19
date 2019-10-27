
package javaapplication1;

import java.io.IOException;
import java.io.File;

public class JavaApplication1 
{    
    private static int MAX_ID_LENGTH = 6;
    
    private static HTTPRequests httpRequest = new HTTPRequests();
    private static Parser parser = new Parser();

    
    public static void main(String[] args) throws IOException 
    {        
        StringBuilder newInput = new StringBuilder("225 w wacker Dr");      
        String cleanedInput = CleanedInput(newInput);
        System.out.print(cleanedInput);
        
        Object output = httpRequest.SendRequest(cleanedInput);
        
        parser.parse(output);
    }
    
    private static String CleanedInput(StringBuilder userInput)
    {        
        for(int i = 0; i < userInput.length(); i++)
        {
            if(userInput.charAt(i) == ' ')
            {
                userInput.setCharAt(i, '_');
            }
        }
 
        userInput.insert(0, InputType(userInput.toString()));
    
        return userInput.toString();
    }
    
    private static String InputType(String userInput)
    {
        Boolean containsLetters = false;
        
        if(userInput.length() <= MAX_ID_LENGTH)
        {
            for(int i = 0; i < userInput.length(); i++)
            {
                if(Character.isLetter(userInput.charAt(i)))
                    containsLetters = true;
            }
            
            if(!containsLetters)
                return "pid/";
        }
        
        if(!ContainsRoadSuffix(userInput))
            return "pn/";
        
        return "pl/";
    }
    
    private static boolean ContainsRoadSuffix(String input)
    {
        String commonRoadSuffixes[] = { "rd", "road", "boulevard", "blvd", "ave", "avenue", "st", "street", "way", "dr", "drive", "pl", "place", "junction", "jct" };
        
        for(int i = 0; i < commonRoadSuffixes.length; i++)
        {
            if(input.toLowerCase().contains(commonRoadSuffixes[i]))
            {
                return true;
            }
        }
        
        return false;
    }
}
