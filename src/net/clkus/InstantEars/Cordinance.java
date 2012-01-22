package net.clkus.InstantEars;

public class Cordinance 
{
	private int altitude = 0;
	private double latitude = 0;
	private double longitude = 0;
		
	public Cordinance()
	{
		
	}
	
	public Cordinance(double Latitude, double Longitude)
	{
		latitude = Latitude;
		longitude = Longitude;
	}
	
	public Cordinance(double Latitude, double Longitude, int Altitude)
	{
		latitude = Latitude;
		longitude = Longitude;
		altitude = Altitude;
	}
	
	public double GetLatitude()
	{
		return latitude;
	}
	
	public double GetLongitude()
	{
		return longitude;
	}
	
	public int GetAltitude()
	{
		return altitude;
	}
	
	public void SetLatitude(double Latitude)
	{
		latitude = Latitude;
	}
	
	public void SetLongitude(double Longitude)
	{
		longitude = Longitude;
	}
	
	public void SetAltitude(int Altitude)
	{
		altitude = Altitude;
	}
}
