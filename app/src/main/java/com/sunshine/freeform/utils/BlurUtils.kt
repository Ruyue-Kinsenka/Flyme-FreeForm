package com.sunshine.freeform.utils

import android.os.Build
import android.graphics.Shader
import android.view.View
import android.view.WindowManager

object BlurUtils {

    fun applyBlurEffect(windowParams: WindowManager.LayoutParams) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_BLUR_BEHIND
            windowParams.blurBehindRadius = 25
        }
    }

    fun applyBlurEffect(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setRenderEffect(android.graphics.RenderEffect.createBlurEffect(25f, 25f, Shader.TileMode.CLAMP))
        }
    }

    fun removeBlurEffect(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setRenderEffect(null)
        }
    }
}
