package net.clkus.InstantEars;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class PhonebookEntries
{
	private Cordinance _cordinance;
	private ieWebServices webServices;
	private String PhonebookResults = null;
	private JSONObject searchResponse = null;
	private JSONObject results = null;
	private JSONObject phoneBook = null;
	private ArrayList<PhonebookEntry> phonebookEntries;
	
	
	public PhonebookEntries(Cordinance cords)
	{
		_cordinance = cords;
		webServices = new ieWebServices();		
		Log.i("XOOM", "Step 1 - Getting Entries");
		GetEntries();
	}
	
	public ArrayList<PhonebookEntry>GetPhonebookEntries()
	{
		return phonebookEntries;
	}
	
	private void GetEntries()
	{
		Log.i("XOOM", "Step 2 - Call web service");
		PhonebookResults = webServices.GetPhonebook(_cordinance);		
		Log.i("XOOM", "Step 6 - Completed Web Service. Setting up Phonebook Entries");
		phonebookEntries = new ArrayList<PhonebookEntry>();
		
		try 
		{
			searchResponse = new JSONObject(PhonebookResults);
			results = searchResponse.getJSONObject("SearchResponse");
			phoneBook = results.getJSONObject("Phonebook");
		} 
		catch (JSONException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Log.i("XOOM", "JSON Error parsing data " + e1.toString());
		}
		try
		{
			JSONArray jArray = phoneBook.getJSONArray("Results");
			Log.i("XOOM", "Setting up Collection");
			for(int i = 0; i <jArray.length(); i++)
			{
				phonebookEntries.add(new PhonebookEntry(jArray.getJSONObject(i)));
			}
		}
		catch(JSONException e)
		{
			Log.i("XOOM", "Error parsing data "+e.toString());
		}			
	}
}