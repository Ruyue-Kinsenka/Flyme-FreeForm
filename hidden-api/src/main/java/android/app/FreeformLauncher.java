package android.app;

import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;
import android.view.Surface;

public class FreeformLauncher {
    private static final String TAG = "FreeformLauncher";

    private static IFreeformOverlayManager sService;

    private static IFreeformOverlayManager getService() {
        if (sService == null) {
            sService = IFreeformOverlayManager.Stub.asInterface(
                ServiceManager.getService("freeform_overlay")
            );
        }
        return sService;
    }

    public static void launch(String packageName) {
        try {
            IFreeformOverlayManager service = getService();
            if (service != null) {
                service.launchInFreeform(packageName);
            }
        } catch (Exception e) {
            //ntd
        }
    }

    public static void createFreeform(String name, IFreeformDisplayCallback callback,
        int width, int height, int densityDpi, boolean secure,
        boolean ownContentOnly, boolean shouldShowSystemDecorations, Surface surface,
        float refreshRate, long presentationDeadlineNanos) {
        try {
            IFreeformOverlayManager service = getService();
            if (service != null) {
                service.createFreeform(name, callback, width, height, densityDpi, secure,
                    ownContentOnly, shouldShowSystemDecorations, surface, refreshRate,
                    presentationDeadlineNanos);
            }
        } catch (Exception e) {
            //ntd
        }
    }

    public static void launchAppOnDisplay(String packageName, String activityName,
        int displayId, int userId) {
        try {
            IFreeformOverlayManager service = getService();
            if (service != null) {
                service.launchAppOnDisplay(packageName, activityName, displayId, userId);
            }
        } catch (Exception e) {
            //ntd
        }
    }

    public static void pauseDisplay(int displayId) {
        try {
            IFreeformOverlayManager service = getService();
            if (service != null) {
                service.pauseDisplay(displayId);
            }
        } catch (Exception e) {
            //ntd
        }
    }

    public static void resumeDisplay(int displayId) {
        try {
            IFreeformOverlayManager service = getService();
            if (service != null) {
                service.resumeDisplay(displayId);
            }
        } catch (Exception e) {
            //ntd
        }
    }

    public static void resizeFreeform(IBinder appToken, int width, int height,
        int densityDpi) {
        try {
            IFreeformOverlayManager service = getService();
            if (service != null) {
                service.resizeFreeform(appToken, width, height, densityDpi);
            }
        } catch (Exception e) {
            //ntd  
        }
    }

    public static void releaseFreeform(IBinder appToken) {
        try {
            IFreeformOverlayManager service = getService();
            if (service != null) {
                service.releaseFreeform(appToken);
            }
        } catch (Exception e) {
            //ntd
        }
    }
}
