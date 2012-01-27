package net.clkus.InstantEars;

import android.util.Log;

public class ieWebServices 
{
	public String GetPhonebook(Cordinance cords)
    {
		Log.i("XOOM", "Step 3 - Starting GetPhonebook method");
		String AppId = "CC1C7755B94C0086D87135F10AD319977784941A";

    	String result;
    	try        
         { 
    		Log.i("XOOM", "Setting Search Uri for Phonebook");
    		String requestStr = "http://api.bing.net/json.aspx?"
    	        
                // Common request fields (required)
                + "AppId=" + AppId
                + "&Query=*"
                + "&Sources=Phonebook"
                
                // Common request fields (optional)
                + "&Version=2.0"
                + "&Market=en-us"
                + "&UILanguage=en"
                + "&Latitude=" + cords.GetLatitude()
                + "&Longitude=" + cords.GetLongitude()
                + "&Radius=1"
                + "&Options=EnableHighlighting"

                // Phonebook-specific request fields (optional)
                + "&Phonebook.Count=25"
                + "&Phonebook.Offset=0"
                + "&Phonebook.FileType=YP"
                + "&Phonebook.SortBy=Relevance" //Distance

                // JSON-specific request fields (optional)
                + "&JsonType=raw"; //callback" + "&JsonCallback=SearchCompleted";    		
    		
    		Log.i("XOOM", "Set PhoneBook Call " + requestStr );
        	RestClient client = new RestClient(requestStr);
        	client.Execute(RequestMethod.GET);
         	Log.i("XOOM", "PhoneBook Get");
         	         	
         	result = client.getResponse();        	
         }        
         catch (Exception e)        
         {            
        	 result = e.getMessage();        	
         }         
         Log.i("XOOM", "PhoneBook Results - " +  result);
         return result;
		
    }

}
