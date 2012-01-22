package net.clkus.InstantEars;

import net.clkus.InstantEars.Globals.Screens;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Comments  extends Activity
{
	Globals globals = Globals.getInstance();
	String LDT = "XOOM";
	String LocID;
	ImageView imgLogo;
	TextView txtName;
	EditText txtServerName;
	EditText txtComments;
	Button cmdCommentSubmit;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Log.i("XOOM", "Comments Activity Started");
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.comments);      	
    	txtName = (TextView)this.findViewById(R.id.txtName);
    	txtServerName = (EditText)this.findViewById(R.id.txtServerName);
    	txtComments = (EditText)this.findViewById(R.id.txtComments);
    	globals.setCurrentScreen(Screens.Comments);
    	txtName.setText(globals.getLocationName());
    }
    
    public void imgLogo_OnClick(View view)
	{
		try
		{
			Log.i(LDT, "Starting ControlPanel");
		   	Intent ControlPanel = new Intent(view.getContext(),ControlPanel.class);
	    	Log.i(LDT, "Starting ControlPanel - Intent is Set");
			startActivityForResult(ControlPanel,0);
			Log.i(LDT, "Starting ControlPanel - Start Activity");
		}
		catch(Exception ex)
		{
			Log.i("XOOM",ex.getMessage());
		}
	}
    
    public void cmdSubmit_OnClick(View view)
    {
    	String Results = "Not Set";
    	try
		{
    		Log.i(LDT, "Post to WebService");
    		Results = PostComment("1",globals.getLocationName(), globals.getLocationAddtress(), 
    				this.txtServerName.getText().toString(),
    				this.txtComments.getText().toString());
    		
    		Log.i(LDT, "Starting ControlPanel");
		   	Intent ControlPanel = new Intent(view.getContext(),ControlPanel.class);
	    	Log.i(LDT, "Starting ControlPanel - Intent is Set");
			startActivityForResult(ControlPanel,0);
			Log.i(LDT, "Starting ControlPanel - Start Activity");
		}
		catch(Exception ex)
		{
			Log.i("XOOM",ex.getMessage());
		}
		Log.i("Xoom",Results);
    }
    
    @Override    
    protected void onResume()
    {
    	super.onResume();
    	globals.setCurrentScreen(Screens.Comments);
    	Log.i(LDT,"Comments OnResume");
    }
    
    @Override
    protected void onPause()
    {
    	super.onResume();
    	globals.setCurrentScreen(Screens.Comments);
    	Log.i(LDT,"Comments OnPause");
    }
    
    @Override
    protected void onStop()
    {
    	super.onStop();
    	globals.setPreviousScreen(Screens.Comments);
    	Log.i(LDT,"Comments OnStop");
    }
        
    private String PostComment(String LocID, String Establishment, String Address, 
    		String ServerName, String Comment)
    {
    	String Uri = "http://instant1.w04.winhost.com/webservices/GPS_Location.svc/PostComment/"; 
    	String result;
    	try        
         { 
    		Uri = Uri + LocID.trim() + "/" + Establishment + "/" + Address + "/" + ServerName + "/" + Comment + "/";
    		Log.i("XOOM", LocID.trim());
    		//@SuppressWarnings("static-access")    		
			Log.i("XOOM",Uri);
			RestClient client = new RestClient(Uri.replace(" ", "%20"));
        	Log.i("XOOM", "Calling PostComment Service" );
        	client.Execute(RequestMethod.GET);
         	Log.i("XOOM", "Completed PostComment Service");          	
         	result = client.getResponse();
         	Log.i("XOOM","PC Results " + client.getErrorMessage());
         }        
         catch (Exception e)        
         {            
        	 result = e.getMessage();
        	 Log.i("XOOM", result);
         }         
         Log.i("XOOM", "Results - " +  result);
         return result;
    }
}
