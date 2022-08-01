package com.EnceptCode.CatchErrorUtils;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

// this class is used to catch the error and display it in the app.
// You Must add this class to your app's manifest file as: android:name=".ErrorReceiver"

public class ErrorReceiver extends Application {

    private Thread.UncaughtExceptionHandler caught_error;

    @Override
    public void onCreate() {
        this.caught_error = Thread.getDefaultUncaughtExceptionHandler();

		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
			Intent intent = new Intent(getApplicationContext(), DisplayErrorActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

			// intent key is Error_message
			intent.putExtra("Error_message", Log.getStackTraceString(throwable));
			@SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);

			AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
			Process.killProcess(Process.myPid());
			System.exit(1);
			caught_error.uncaughtException(thread, throwable);
		});
		super.onCreate();
	}
}

//powered By Encept Corporation