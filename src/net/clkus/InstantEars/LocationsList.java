package net.clkus.InstantEars;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
//import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


public class LocationsList extends Activity
{
	Globals globals = Globals.getInstance();
		
	ListView lstLocations;	
	String LDT = "XOOM";
	
	Cordinance cords;
	GPS_Services gps;
	PhonebookEntries phonebookEntries;
	ArrayList<PhonebookEntry> entries;
	PhonebookEntryAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.locationslist);
		lstLocations = (ListView)this.findViewById(R.id.lstLocations);
		lstLocations.setOnItemClickListener(new OnItemClickListener()
    	{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView name = (TextView)arg1.findViewById(R.id.txtName);				
				TextView address = (TextView)arg1.findViewById(R.id.txtAddress);
				TextView phone = (TextView)arg1.findViewById(R.id.txtPhone);
				
				globals.setLocationName((String)name.getText());
				globals.setLocationAddress((String)address.getText());
				globals.setLocationPhone((String)phone.getText());
				GotoScreen(globals.getNextScreen(), arg1.getRootView());			
			}    		
    	});
		
		gps = new GPS_Services(globals.getAppContext());
		gps.StartListener(50000, 0);		
		cords = new Cordinance(gps.latitude,gps.longitude);	
		phonebookEntries = new PhonebookEntries(cords);	
		entries = phonebookEntries.GetPhonebookEntries();
		
		adapter = new PhonebookEntryAdapter(entries);
		lstLocations.setAdapter(adapter);
	}
	
	private void GotoScreen(Globals.Screens screen, View view)
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
    }
}

