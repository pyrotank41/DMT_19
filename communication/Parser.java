/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

public class Parser 
{   
    public void parse(Object file)
    {
        String contentArray[] = file.toString().split(",");
        for(int i = 0; i < contentArray.length; i++)
        {
            if(contentArray[i].split(":").length > 1)
                contentArray[i] = contentArray[i].split(":")[1];
            contentArray[i].replaceAll("\"", "");
            System.out.println(contentArray[i]);
        }      
    }  
    
    public void RetrieveLocations()
    {
    }
}
