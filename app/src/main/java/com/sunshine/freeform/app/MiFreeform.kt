package com.sunshine.freeform.app

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.*
import com.google.android.material.color.DynamicColors
import com.sunshine.freeform.BuildConfig
import com.sunshine.freeform.IControlService
import com.sunshine.freeform.service.ControlService
import com.sunshine.freeform.utils.ServiceUtils
import com.sunshine.freeform.utils.SystemServiceHelper
import org.lsposed.hiddenapibypass.HiddenApiBypass
import java.lang.StringBuilder

/**
 * @author sunshine
 * @date 2021/3/17
 */
class MiFreeform : Application() {
    val isRunning = MutableLiveData(false)
    var controlService: IControlService? = null

    private val userServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isRunning.value = false
            controlService = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            controlService = IControlService.Stub.asInterface(service)
            if (controlService!!.init()) isRunning.value = true
        }
    }

    companion object {
        lateinit var me: MiFreeform
        private const val TAG = "MiFreeForm"
        const val PACKAGE_NAME = "com.sunshine.freeform"
        const val VERSION = 1
        const val VERSION_PRIVACY = 1
        const val APP_SETTINGS_NAME = "app_settings"

        private val log = StringBuilder()

        fun addLog(tag: String, functionName: String,  e: Exception) {
            log.append("$tag,$functionName:${e.message}")
        }
    }

    override fun onCreate() {
        super.onCreate()
        me = this

        DynamicColors.applyToActivitiesIfAvailable(this);

        initSystemServices()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            HiddenApiBypass.addHiddenApiExemptions("")
        }
    }

    private fun initSystemServices() {
        try {
            SystemServiceHelper.init(this)
            ServiceUtils.init(this)
            isRunning.postValue(true)
            Log.i(TAG, "System services initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize system services", e)
            isRunning.postValue(false)
        }
    }

    fun pingServiceBinder(): Boolean {
        return controlService?.asBinder()?.pingBinder() == true
    }

    fun execShell(command: String, useRoot: Boolean): Boolean {
        return controlService?.execShell(command, useRoot)!!
    }
}