package net.clkus.InstantEars;

import java.util.ArrayList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class PhonebookEntryAdapter extends ArrayAdapter<PhonebookEntry> 
{
	ArrayList<PhonebookEntry> items;
	Globals globals = Globals.getInstance();
	
	public PhonebookEntryAdapter(ArrayList<PhonebookEntry> items) 
    {         
		super(Globals.getInstance().getAppContext(), R.layout.row, items);     
		this.items = items;
		Log.i("XOOM", "Listbox AdapterView Intialized");
    }

    @Override
     public View getView(int position, View convertView, ViewGroup parent) 
    {
    	Log.i("XOOM", "Listbox AdapterView Started");
    	View v = convertView;         
         try
         {
        	 if (v == null) 
             {
                 LayoutInflater vi = LayoutInflater.from(globals.getAppContext());
                 v = vi.inflate(R.layout.row, parent, false);
                 Log.i("XOOM", "Listbox AdapterView = null");
             }
             PhonebookEntry o = items.get(position);
             if (o != null) 
             {
            	 Log.i("XOOM", "Listbox AdapterView != null");
            	 TextView txtName = (TextView) v.findViewById(R.id.txtName);
				 TextView txtAddress = (TextView) v.findViewById(R.id.txtAddress);
				 TextView txtPhone = (TextView) v.findViewById(R.id.txtPhone);
				 
				 Log.i("XOOM", "Listbox Adapter Items" + txtName.getText() + " " + txtAddress.getText() + " " + txtPhone.getText());
				 if (txtName != null) 
				 {
					 txtName.setText("" + o.GetBusinessName());
				 }
				 if(txtAddress != null)
				 {
					 txtAddress.setText("" + o.GetAddress());
				 }
				 if(txtPhone != null)
				 {
					 txtPhone.setText("" + o.GetPhoneNumber());
				 }
             }
         }
         catch(Exception ex)
         {
        	Log.i("XOOM", "Listbox Adapter Error" + ex.getMessage()); 
         }
         Log.i("XOOM", "Listbox Adapter - Completed"); 
         return v;
     }
}
