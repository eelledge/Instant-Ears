package net.clkus.InstantEars;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import com.google.gson.Gson;

public class PhonebookEntries
{
	private static PhonebookEntries _Instance;
	
	private Cordinance _cordinance;
	private ieWebServices webServices;
	private String PhonebookResults = null;
	private JSONObject searchResponse = null;
	private JSONObject results = null;
	private JSONObject phoneBook = null;
	private ArrayList<PhonebookEntry> phonebookEntries;
		
	public PhonebookEntries()
	{
		_Instance = null;
		webServices = new ieWebServices();		
		Log.i("XOOM", "Set Instance");		
	}
	
	public static synchronized PhonebookEntries getInstance()
	{
		if(null==_Instance)
		{
			_Instance = new PhonebookEntries();
		}
		return _Instance;
	}
	
	public ArrayList<PhonebookEntry>GetPhonebookEntries()
	{
		return phonebookEntries;
	}
	
	public void GetEntries(Cordinance cords)
	{
		_cordinance = cords;
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
			phonebookEntries.clear();
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

	public PhonebookEntry GetEntryByID(int ID)
	{
		return phonebookEntries.get(ID);
	}
	public String ConvertEntry2Json(int ID)
	{
		PhonebookEntry entry = phonebookEntries.get(ID);
		Gson gson = new Gson();
		
		return gson.toJson(entry);
		
	}
}