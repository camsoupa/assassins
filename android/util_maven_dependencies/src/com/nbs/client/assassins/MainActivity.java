/**
 * 
 */
package com.nbs.client.assassins;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.FragmentById;
//import com.slidingmenu.lib.SlidingMenu;
//import com.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * @author cam
 *
 */

@EActivity
public class MainActivity extends SherlockFragmentActivity {
	
	private MenuFragment menuFragment;
	
	@FragmentById(R.id.fragment_map)
	MapFragment_ map;
	
	private final String TAG = "MainActivity";
	
	IntentFilter playerStateChangedFilter; 
	
	public MainActivity() {
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        
        if(GCMRegistrar.isRegistered(this))
        {
        	GCMRegistrar.unregister(this);//TODO: Just for initial testing remove this line!
        } else
        {
        	GCMRegistrar.register(this, GCMUtilities.SENDER_ID);
        }

        playerStateChangedFilter = new IntentFilter();
        playerStateChangedFilter.addAction("PLAYER_STATE_CHANGED");      
        
        startService(new Intent(this, LocationService_.class));
        
 		//setBehindContentView(R.layout.menu_frame);

 		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
 		menuFragment = new MenuFragment();
 		t.replace(R.id.menu_frame, menuFragment);
 		t.commit();

/* 		SlidingMenu sm = getSlidingMenu();
 		sm.setShadowWidthRes(R.dimen.shadow_width);
 		sm.setShadowDrawable(R.drawable.shadow);
 		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
 		sm.setFadeDegree(0.35f);
 		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
 		sm.setMode(SlidingMenu.LEFT);*/
 		
 		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 		
 		//setSlidingActionBarEnabled(false);
 		
 		setContentView(R.layout.activity_main);
    }
    
    @Override
    public void onDestroy() 
    {
        GCMRegistrar.onDestroy(this);
        super.onDestroy();
    }

	@Override
	protected void onPause() 
	{
	    unregisterReceiver(playerStateChangedReceiver);
	    super.onPause();
	}
	

	@Override
	protected void onResume() 
	{
	    super.onResume();
	    registerReceiver(playerStateChangedReceiver, playerStateChangedFilter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	        toggle();
	        return true;
	    }
	    
		return super.onOptionsItemSelected(item);
	}
	
	
	   private BroadcastReceiver playerStateChangedReceiver = new BroadcastReceiver() 
	    {
	        @Override
	        public void onReceive(Context context, Intent intent) 
	        {
	    		Log.v(TAG, "map " + map);
	        	
	        	if(map != null)
	    		{
		    		User u = new User();
		    		u.installId = intent.getStringExtra("installId");
		    		u.latitude = intent.getDoubleExtra("latitude", 0);
		    		u.longitude = intent.getDoubleExtra("longitude", 0);
	
	
		    		
		    		Marker m = map.getMap().addMarker(
		    		new MarkerOptions()
		    		.position(new LatLng(u.latitude, u.longitude))
		    		.title(u.installId)
		    		.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	    		}

	        }
	    };
}