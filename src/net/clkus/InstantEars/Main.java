package net.clkus.InstantEars;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.*;

public class Main extends Activity //implements LocationListener
{	
	ListView lstBusiness;	
	ImageView imgLogo;
	
	Boolean slideLstBusiness = true;
	Display display;
	Matrix matrix = new Matrix();
	Toast toast;
	Context context;	
	ArrayList<PhonebookEntry> entries;
	PhonebookEntryAdapter adapter;
	Runnable viewEntries;
	GPS_Services gps;
	ieWebServices webServices;
	PhonebookEntries phonebookEntries;
	String LDT = "XOOM";
	Cordinance cords; 
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);         
        	
    	Log.d(LDT, "Initializing UI");
    	// Do your heavy loading here  
    	InitializeUI();
    	
		cords = new Cordinance(gps.latitude,gps.longitude);
		phonebookEntries = new PhonebookEntries(cords);	
		entries = phonebookEntries.GetPhonebookEntries();
		adapter = new PhonebookEntryAdapter(entries);
		lstBusiness.setAdapter(adapter);
		
    } 
    
    private void InitializeUI()
    {
    	gps = new GPS_Services(this);
    	entries = null;
    	display = getWindowManager().getDefaultDisplay();
    	Log.d(LDT, "Initializing Wigets");
    	imgLogo = (ImageView)this.findViewById(R.id.imgLogo);    	
    	
    	lstBusiness = (ListView)this.findViewById(R.id.lstBusiness);    
    	Log.d(LDT, "Initializing Wigets - COMPLETED");
    	
    	context = getApplicationContext();    	
    	Log.d(LDT, "Initializing Command Buttons - COMPLETED");
    	
    	/*lstBusiness.setOnItemClickListener(new OnItemClickListener()
    	{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
			{
				//TextView txtName = (TextView)arg1.findViewById(R.id.txtName);
				//TextView txtAddress = (TextView)arg1.findViewById(R.id.txtAddress);
				//TextView txtPhone = (TextView)arg1.findViewById(R.id.txtPhone);
				
				if(slideLstBusiness)
				{
					lstBusiness.offsetLeftAndRight(display.getWidth() - 75);
					lstBusiness.invalidate();
					slideLstBusiness = false;
				}
				else
				{
					lstBusiness.offsetLeftAndRight((display.getWidth()* -1)+ 75);
					lstBusiness.invalidate();
					slideLstBusiness = true;
				}
				
				//GotoScreen(Globals.getInstance().NextScreen, arg1.getRootView());
				
			}    		
    	});*/
    	Log.d(LDT, "Initializing List - COMPLETED");	
    	Log.i("XOOM", "TESTING - setOnNewLocationListener");		
    	gps.setOnNewLocationListener(new OnNewLocationListener()
    	{
			@Override
			public void onNewLocations() 
			{
				showToastMessage("New Location");
				// TODO Auto-generated method stub
				cords = new Cordinance(gps.latitude,gps.longitude);
	    		phonebookEntries = new PhonebookEntries(cords);	
	    		entries = phonebookEntries.GetPhonebookEntries();
	    		adapter = new PhonebookEntryAdapter(entries);
	    		//lstBusiness.setAdapter(adapter);
			}
    		
    	});    	
    }
    
    
    
    /*private void GotoScreen(Globals.Screens screen, View view)
    {
    	Intent curActivity;
    	switch (screen)
    	{
    	case Comments:
    		curActivity = new Intent(view.getContext(),Comments.class);
    		break;
    	case Survey:
    		curActivity = new Intent(view.getContext(),Survey.class);
    		break;
    	default:
    		curActivity = new Intent(view.getContext(),ControlPanel.class);    		
    	}    	
    	startActivityForResult(curActivity,0);
    }*/
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }
    
    private void showToastMessage(String msg)
    {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override 
    public void onConfigurationChanged(Configuration newConfig) 
    { 
    	super.onConfigurationChanged(newConfig);
    	// Checks the orientation of the screen 
    	if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 
    	{ 
    	    Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show(); 
    	} 
    	else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
    	{ 
    	    Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show(); 
    	} 
    	// Checks whether a hardware keyboard is available 
    	if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) 
    	{ 
    	    Toast.makeText(this, "keyboard visible", Toast.LENGTH_SHORT).show(); 
    	} 
    	else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) 
    	{ 
    	    Toast.makeText(this, "keyboard hidden", Toast.LENGTH_SHORT).show();
    	}
    }
    
    @Override    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) 
        {
            case R.id.icon:     Toast.makeText(this, "You pressed GPS Options!", Toast.LENGTH_LONG).show();
                                break;
            case R.id.text:     Toast.makeText(this, "You pressed the Search Options!", Toast.LENGTH_LONG).show();
                                break;
            case R.id.icontext: Toast.makeText(this, "You pressed the icon and text!", Toast.LENGTH_LONG).show();
                                break;
        }
        return true;
    }
    
    /** Initializes the UI widgets **/    
    
    @Override    
    protected void onResume()
    {
    	super.onResume();
    	Log.i("XOOM", "Main-onResume - StartListener");
    	if (gps !=null)
    		gps.StartListener(5000,0);
    	Log.i("XOOM", "Main-onResume - Exit StartListener");
    }
    
    @Override
    protected void onPause()
    {
    	gps.StopListener();
    	Log.i("XOOM", "StopLister");
    	super.onResume();
    }
    
    @Override
    protected void onStop()
    {
    	super.onStop();
    	finish();    	
    }
        
    /*private String PostLocation(String Latitude, String Longitude, String Altitude)
    {
    	String Uri = "http://instant1.w04.winhost.com/webservices/GPS_Location.svc/Location/"; 
    	String result;
    	
    	try        
         { 
    		//@SuppressWarnings("static-access")
    		Log.i("XOOM-PL",Uri + Longitude + "/" +  Latitude + "/" +  Altitude + "/");
			RestClient client = new RestClient(Uri + Longitude + "/" +  Latitude + "/" +  Altitude + "/");
        	Log.i("XOOM-PL", Uri + Longitude + "/" + Latitude + "/" + Altitude +"/" );
        	Log.i("XOOM-PL", "CALLING SERVICE" );
         	client.Execute(RequestMethod.GET);
         	Log.i("XOOM-PL", "COMPLETED CALLING SERVICE"); 
         	
         	result = client.getResponse();
         	LocID = result;
         }        
         catch (Exception e)        
         {            
        	 result = e.getMessage();        
         }         
         Log.i("XOOM-PL", "Results - " + result);
         return result;
    }
    
    private String PostAddress(String LocID, String Address1, String Address2, 
    		String City, String State, String ZipCode)
    {
    	String Uri = "http://instant1.w04.winhost.com/webservices/GPS_Location.svc/PostAddress/"; 
    	String result;
    	try        
         { 
    		Uri = Uri + LocID.trim() + "/" + Address1 + "/" + Address2 + "/" + City + "/" + State + "/" + ZipCode + "/";
    		Log.i("XOOM-PA", LocID.trim());
    		//@SuppressWarnings("static-access")    		
			Log.i("XOOM-PA1",Uri);
			RestClient client = new RestClient(Uri);
        	Log.i("XOOM-PA", "Calling PostAddress Service" );
        	client.Execute(RequestMethod.GET);
         	Log.i("XOOM-PA", "Completed PostAddress Service");          	
         	result = client.getResponse();
         	Log.i("XOOM-PA","PA Results " + client.getErrorMessage());
         }        
         catch (Exception e)        
         {            
        	 result = e.getMessage();
        	 Log.i("XOOM-Error", result);
         }         
         Log.i("XOOM-PA", "Results - " +  result);
         return result;
    }
    
    private String PostName(String LocID, String Name)
    {
    	String Uri = "http://instant1.w04.winhost.com/webservices/GPS_Location.svc/PostName/"; 
    	String result;
    	try        
         { 
    		Uri = Uri + LocID.trim() + "/" + Name + "/";
    		Log.i("XOOM-PA", LocID.trim());
    		//@SuppressWarnings("static-access")    		
			Log.i("XOOM-PN",Uri);
			RestClient client = new RestClient(Uri);
        	Log.i("XOOM-PN", "Calling PostName Service" );
        	client.Execute(RequestMethod.GET);
         	Log.i("XOOM-PN", "Completed PostName Service");          	
         	result = client.getResponse();
         	Log.i("XOOM-PN","PN Results " + client.getErrorMessage());
         }        
         catch (Exception e)        
         {            
        	 result = e.getMessage();
        	 Log.i("XOOM-PN Error", result);
         }         
         Log.i("XOOM-PN", "Results - " +  result);
         return result;
    }
    
    private String PostComment(String LocID, String Comment)
    {
    	String Uri = "http://instant1.w04.winhost.com/webservices/GPS_Location.svc/PostComment/"; 
    	String result;
    	try        
         { 
    		Uri = Uri + LocID.trim() + "/" + Comment + "/";
    		Log.i("XOOM-PC", LocID.trim());
    		//@SuppressWarnings("static-access")    		
			Log.i("XOOM-PC",Uri);
			RestClient client = new RestClient(Uri);
        	Log.i("XOOM-PC", "Calling PostComment Service" );
        	client.Execute(RequestMethod.GET);
         	Log.i("XOOM-PC", "Completed PostComment Service");          	
         	result = client.getResponse();
         	Log.i("XOOM-PC","PC Results " + client.getErrorMessage());
         }        
         catch (Exception e)        
         {            
        	 result = e.getMessage();
        	 Log.i("XOOM-PC Error", result);
         }         
         Log.i("XOOM-PC", "Results - " +  result);
         return result;
    }
    */	
}