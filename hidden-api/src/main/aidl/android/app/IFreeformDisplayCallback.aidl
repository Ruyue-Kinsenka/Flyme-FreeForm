package android.app;

interface IFreeformDisplayCallback {
    void onDisplayCreated(int displayId);
    void onDisplayDestroyed(int displayId);
    void onDisplayResized(int displayId, int width, int height);
    void onError(int errorCode, String message);
}
