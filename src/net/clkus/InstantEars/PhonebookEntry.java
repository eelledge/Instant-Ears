package net.clkus.InstantEars;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class PhonebookEntry 
{
	private int peReviewCount;
	private String peBingUniqueId;
	private String peBusinessName;
	private String pePhoneNumber;
	private String peAddress;
	private String peCity;
	private String peState;
	private String peZipCode;
	private String peDisplayUrl;
	private String peUrl;
	private double peLatitude;
	private double peLongitude;
	private double peUserRating;
	
	public PhonebookEntry(JSONObject entry)
	{
		try
		{
			peBingUniqueId = entry.getString("UniqueId");
			peBusinessName = entry.getString("Business");
			pePhoneNumber = entry.getString("PhoneNumber");
			peAddress = entry.getString("Address");
			peCity = entry.getString("City");
			peState = entry.getString("StateOrProvince");
			peZipCode = entry.getString("PostalCode");
			peLatitude = entry.getDouble("Latitude");
			peLongitude = entry.getDouble("Longitude");
			peReviewCount = entry.optInt("ReviewCount",0);
			peUserRating = entry.optDouble("UserRating",0);
			peDisplayUrl = entry.getString("DisplayUrl");
			peUrl = entry.getString("Url");
			
			Log.i("XOOM", entry.toString());
		}
		catch (JSONException e1)
		{
			Log.e("XOOM", "JSON Error - " + e1.toString());
		}
	}
	
	public String GetBingUniqueId()
	{
		return peBingUniqueId;
	}
	
	public String GetDisplayUrl()
	{
		return peDisplayUrl;
	}
	
	public String GetUrl()
	{
		return peUrl;
	}
	
	public String GetBusinessName()
	{
		return peBusinessName;
	}
	
	public String GetPhoneNumber()
	{
		return pePhoneNumber;
	}
	
	public String GetAddress()
	{
		return peAddress;
	}
	
	public String GetCity()
	{
		return peCity;
	}
	
	public String GetState()
	{
		return peState;
	}
	
	public String GetZipCode()
	{
		return peZipCode;
	}
	
	public double GetLatitude()
	{
		return peLatitude;
	}
	
	public double GetLongitude()
	{
		return peLongitude;
	}
	
	public double GetUserRating()
	{
		return peUserRating;
	}
	
	public int GetReviewCount()
	{
		return peReviewCount;
	}
}
