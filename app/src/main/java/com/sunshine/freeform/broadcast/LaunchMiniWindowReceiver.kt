package com.sunshine.freeform.broadcast

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.sunshine.freeform.ui.freeform.FreeformService

class LaunchMiniWindowReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val packageName = intent.getStringExtra("packageName")
        val activityName = intent.getStringExtra("activityName")
        val userId = intent.getIntExtra("userId", -1)

        if (packageName == null || activityName == null) {
            return
        }

        val target = Intent(Intent.ACTION_MAIN)
            .setComponent(ComponentName(packageName, activityName))
            .setPackage(packageName)
            .addCategory(Intent.CATEGORY_LAUNCHER)

        context.startService(
            Intent(context, FreeformService::class.java)
                .setAction(FreeformService.ACTION_START_INTENT)
                .putExtra(Intent.EXTRA_INTENT, target)
                .putExtra(Intent.EXTRA_COMPONENT_NAME, target.component)
                .putExtra(Intent.EXTRA_USER, userId)
                .putExtra(EXTRA_LAUNCH_MINI_MODE, true)
        )
    }

    companion object {
        const val ACTION_LAUNCH_MINI_WINDOW = "org.avium.LAUNCHER_MINI_WINDOW"
        const val EXTRA_LAUNCH_MINI_MODE = "launch_mini_mode"
    }
}
