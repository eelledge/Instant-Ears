package net.clkus.InstantEars;

import java.util.Locale;

import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class GPS_Services implements LocationListener
{
	LocationManager locManager;
	
	Geocoder geoCoder;
	private Location location;
	private Context mContext;
	
	IEAddress ieAddress;
	double longitude, latitude, altitude;
	int Counter;
		
	public GPS_Services(Context context)
	{
		mContext = context;
		locManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		geoCoder = new Geocoder(mContext, Locale.getDefault()); 
		location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        if (location != null)
			onLocationChanged(location);
	}
	
	public void StartListener(int Interval, int Distants)
	{		
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
				Interval, Distants, this );
	}
	
	public void StopListener()
	{
		locManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {

		GetLocation(location);
		
		if (this.onNewLocationListener != null)
		{
			this.onNewLocationListener.onNewLocations();
		}
	}
	
	protected OnNewLocationListener onNewLocationListener;
	
	public void setOnNewLocationListener(OnNewLocationListener l)
	{
		this.onNewLocationListener = l;
	}
	
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	private void GetLocation(Location location)
    {
		try 
		{	
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			altitude = location.getAltitude();
		} 
    	catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("XOOM","GPS_Services - Error - " + e.getMessage());
		}
    }

	
}
