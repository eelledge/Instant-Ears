package net.clkus.InstantEars;

import android.util.Log;
import java.lang.String;

public class IEAddress 
{
	private String _city;
	private String _state;
	private String _zipCode;
	
	public IEAddress(String CityStZip)
	{
		int endCity = CityStZip.indexOf(",");
		_city = (String)CityStZip.subSequence(0, endCity);
		_state = (String)CityStZip.subSequence(endCity + 2, endCity + 4);
		Log.i("IEAddress",String.valueOf(endCity));
		Log.i("IEAddress",String.valueOf(CityStZip.length()));
		_zipCode = (String)CityStZip.subSequence(endCity + 5, CityStZip.length());		
	}
	
	public String getCity()
	{
		return _city.replaceAll(" ", "%20");
	}
	
	public String getState()
	{
		return _state.replaceAll(" ", "%20");
	}
	
	public String getZipCode()
	{
		return _zipCode.replaceAll(" ", "%20");
	}
	
}
