package net.clkus.InstantEars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Survey  extends Activity
{
	String LDT = "XOOM";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Log.i("XOOM", "Survey Activity Started");
    	super.onCreate(savedInstanceState);
    	 setContentView(R.layout.survey);
    }
    
    public void imgLogo_OnClick(View view)
	{
		try
		{
			Log.i(LDT, "Starting Control Panel");
		   	Intent actControlPanel = new Intent(view.getContext(),ControlPanel.class);
	    	Log.i(LDT, "Starting Control Panel - Intent is Set");
			startActivityForResult(actControlPanel,0);
			Log.i(LDT, "Starting Control Panel - Start Activity");
		}
		catch(Exception ex)
		{
			Log.i("XOOM",ex.getMessage());
		}
	}
   
}
