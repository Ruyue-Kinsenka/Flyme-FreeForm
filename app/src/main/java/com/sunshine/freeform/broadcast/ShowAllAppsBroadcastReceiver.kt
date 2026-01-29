package com.sunshine.freeform.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.sunshine.freeform.app.MiFreeform

class ShowAllAppsBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val ACTION_SHOW_ALL_APPS = "com.sunshine.freeform.SHOW_ALL_APPS"
        private const val TAG = "ShowAllAppsReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_SHOW_ALL_APPS) {
            Log.d(TAG, "Received SHOW_ALL_APPS broadcast")
            try {
                val sp = context.getSharedPreferences(MiFreeform.APP_SETTINGS_NAME, Context.MODE_PRIVATE)
                val currentValue = sp.getBoolean("to_show_all_apps", false)
                sp.edit().putBoolean("to_show_all_apps", !currentValue).apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
