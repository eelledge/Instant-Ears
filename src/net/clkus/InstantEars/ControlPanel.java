package net.clkus.InstantEars;

import net.clkus.InstantEars.Globals.Screens;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

public class ControlPanel  extends Activity
{
	protected Dialog mSplashDialog;
	Globals globals = Globals.getInstance();
	
	String LDT = "XOOM";	
	ImageView imgLogo;
	TableLayout tblControlPanel;
	GPS_Services gps;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		Log.i("XOOM", "ControlPanel Activity Started");
		setContentView(R.layout.controlpanel);   
		globals.setAppContext(getApplicationContext());
		MyStateSaver data = (MyStateSaver) getLastNonConfigurationInstance(); 
		
		if (data != null) 
		{        
			//Show splash screen if still loading        
			if (data.showSplashScreen) 
			{            
				showSplashScreen();			
			}        
			setContentView(R.layout.controlpanel);
			// Rebuild your UI with your saved state here    
		} 
		else 
		{        
			if(globals.getShowSplashScreen())
			{
				showSplashScreen();   
				globals.setShowSplashScreen(false);
			}
		 	setContentView(R.layout.controlpanel); 
	    }
		globals.setCurrentScreen(Screens.ControlPanel);
    }
    
    public void cmdComments_OnClick(View view)
	{
		try
		{
			globals.setNextScreen(Screens.Comments);
			GetCurrentLocation(view);
		}
		catch(Exception ex)
		{
			Log.i("XOOM",ex.getMessage());
		}
	}
    
    public void cmdSurvey_OnClick(View view)
	{
		try
		{
			globals.setNextScreen(Screens.Survey);
			GetCurrentLocation(view);
		}
		catch(Exception ex)
		{
			Log.i("XOOM",ex.getMessage());
		}
	}
    
    private void GetCurrentLocation(View view)
    {
    	Log.i(LDT, "Starting LocationsList");
	   	Intent CurLoc = new Intent(view.getContext(),LocationsList.class);
    	Log.i(LDT, "Starting LocationsList - Intent is Set");
		startActivityForResult(CurLoc,0);
		Log.i(LDT, "Starting LocationsList - Start Activity");
    }
    
    
    @Override    
    protected void onResume()
    {
    	super.onResume();
    	globals.setCurrentScreen(Screens.ControlPanel);
    	Log.i(LDT,"ControlPanel OnResume");
    }
    
    @Override
    protected void onPause()
    {
    	super.onResume();
    	globals.setCurrentScreen(Screens.ControlPanel);
    	Log.i(LDT,"ControlPanel OnPause");
    }
    
    @Override
    protected void onStop()
    {
    	super.onStop();
    	globals.setPreviousScreen(Screens.ControlPanel);
    	Log.i(LDT,"ControlPanel OnStop");
    }
    
    @Override
    public Object onRetainNonConfigurationInstance() 
    {    
    	MyStateSaver data = new MyStateSaver();    
    	// Save your important data here     
    	if (mSplashDialog != null) 
    	{        
    		data.showSplashScreen = true;        
    		removeSplashScreen();    
    	}    
    	return data;    
    }
    
  //Removes the Dialog that displays the splash screen 	 
	protected void removeSplashScreen() 
	{    
		if (mSplashDialog != null) 
		{        
			mSplashDialog.dismiss();        
			mSplashDialog = null;    
		}
	} 
	
	//Shows the splash screen over the full Activity 	
	protected void showSplashScreen() 
	{    
		mSplashDialog = new Dialog(this, R.style.SplashScreen);    
		mSplashDialog.setContentView(R.layout.splashscreen);    
		mSplashDialog.setCancelable(false);    
		mSplashDialog.show();     
		// Set Runnable to remove splash screen just in case    
		final Handler handler = new Handler();    
		handler.postDelayed(new Runnable() 
		{      
			@Override      
			public void run() 
			{        
				removeSplashScreen();      
			}    
		}, 2000);
	}  
	
	// Simple class for storing important data across config changes 
	private class MyStateSaver 
	{    
		public boolean showSplashScreen = false;    
		// Your other important fields here}
	}	
}
