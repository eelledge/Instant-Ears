package net.clkus.InstantEars;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceGps extends Service
{
	String LDT = "XOOM";
	
	public ServiceGps()
	{
		Log.i(LDT, "ServiceGps.ServiceGps");
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.i(LDT, "GPSLoggerService.onCreate().");

		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(LDT, "GPSLoggerService.onStart().");

		super.onStart(intent, startId);
	}

	public String onStartCommand(Intent intent, int flags, int startId) {
		Log.i(LDT, "GPSLoggerService.onStartCommand().");

		return Service.LOCATION_SERVICE;
	}

}
