package com.nbs.client.assassins.services;

import com.nbs.client.assassins.models.App;
import com.nbs.client.assassins.models.Repository;
import com.nbs.client.assassins.models.User;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {

	private static final String TAG = "BootCompletedReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		context.startService(new Intent(context, LocationService_.class));
		context.startService(new Intent(context, NotificationService_.class)
			.setAction(NotificationService.SET_MATCH_REMINDER_ALARMS));
	}

}
