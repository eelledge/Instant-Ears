package net.clkus.InstantEars;

import android.content.Context;

public class Globals 
{
	//Screen Enumeration
	public enum Screens {ControlPanel, Comments, Survey}
	
	private static Globals _Instance;	
	
	//Screens Information
	private Screens prevScreen;
	private Screens currScreen;
	private Screens nxtScreen;
	
	private boolean showSplashScreen;
	private Context appContext;
	
	//Location Information
	private String curName = "";
	private String curAddress = "";
	private String curPhone = "";
	
	protected Globals()
	{
		_Instance = null;
		
		prevScreen = null;
		currScreen = Screens.ControlPanel;
		nxtScreen = null;
		
		showSplashScreen = true;
		appContext = null;
	}
	
	public static synchronized Globals getInstance()
	{
		if(null==_Instance)
		{
			_Instance = new Globals();
		}
		return _Instance;
	}
	
	//Misc Properties
	public void setAppContext(Context applicationContext)
	{
		appContext = applicationContext;
	}
	
	public Context getAppContext()
	{
		return appContext;
	}

	public void setShowSplashScreen(boolean show)
	{
		showSplashScreen = show;
	}
	
	public boolean getShowSplashScreen()
	{
		return showSplashScreen;
	}
	
	//Location Information Properties
	public void setLocationName(String name)
	{
		curName = name;		
	}
	
	public String getLocationName()
	{
		return curName;
	}
	
	public void setLocationAddress(String address)
	{
		curAddress = address;
	}
	
	public String getLocationAddtress()
	{
		return curAddress;
	}
	
	public void setLocationPhone(String phone)
	{
		curPhone = phone;
	}
	
	public String getLocationPhone()
	{
		return curPhone;
	}
	
	//Screen Information Properties
	public void setPreviousScreen(Screens previousScreen)
	{
		prevScreen = previousScreen;
	}
	
	public Screens getPreviousScreen()
	{
		return prevScreen;
	}
	
	public void setCurrentScreen(Screens currentScreen)
	{
		currScreen = currentScreen;
	}
	
	public Screens getCurrentScreen()
	{
		return currScreen;
	}
	
	public void setNextScreen(Screens nextScreen)
	{
		nxtScreen = nextScreen;
	}
	public Screens getNextScreen()
	{
		return nxtScreen;
	}
	
	public void setCurLot(double Lon)
	{
		    
	}
}

