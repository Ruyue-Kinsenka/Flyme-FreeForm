package com.sunshine.freeform.utils

import android.content.Context
import android.os.SystemProperties

object PreferenceHelper {
    private const val POPUP_VIEW_MODE_PROPERTY = "persist.avium.popup_view"
    private const val BUBBLE_MODE = "bubble"

    fun getPopupViewMode(context: Context, default: Boolean = false): Boolean {
        val mode = SystemProperties.get(POPUP_VIEW_MODE_PROPERTY, "")
        return mode == BUBBLE_MODE
    }
}
