package com.sunshine.freeform.utils;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.IActivityTaskManager;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.input.IInputManager;
import android.os.ServiceManager;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManager;

public class SystemServiceHelper {
    private static final String TAG = "SystemServiceHelper";

    private static IActivityManager sActivityManager;
    private static IActivityTaskManager sActivityTaskManager;
    private static IWindowManager sWindowManager;
    private static IInputManager sInputManager;
    private static boolean sInitialized = false;

    public static boolean init(Context context) {
        if (sInitialized) {
            return true;
        }

        try {
            sActivityManager = IActivityManager.Stub.asInterface(
                ServiceManager.getService(Context.ACTIVITY_SERVICE)
            );
            sActivityTaskManager = IActivityTaskManager.Stub.asInterface(
                ServiceManager.getService("activity_task")
            );
            sWindowManager = IWindowManager.Stub.asInterface(
                ServiceManager.getService(Context.WINDOW_SERVICE)
            );
            sInputManager = IInputManager.Stub.asInterface(
                ServiceManager.getService(Context.INPUT_SERVICE)
            );

            sInitialized = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static IActivityManager getActivityManager() {
        return sActivityManager;
    }

    public static IActivityTaskManager getActivityTaskManager() {
        return sActivityTaskManager;
    }

    public static IWindowManager getWindowManager() {
        return sWindowManager;
    }

    public static IInputManager getInputManager() {
        return sInputManager;
    }

    public static boolean isInitialized() {
        return sInitialized;
    }
}
