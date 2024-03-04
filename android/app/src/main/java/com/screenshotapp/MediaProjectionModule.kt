package com.screenshotapp

import android.app.Activity
import android.content.Intent
import android.media.projection.MediaProjectionManager
import androidx.core.app.ActivityCompat
import com.facebook.react.bridge.*

class MediaProjectionModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext), ActivityEventListener {
    private var promise: Promise? = null

    override fun getName(): String = "MediaProjectionModule"

    @ReactMethod
    fun startScreenCapture(promise: Promise) {
        this.promise = promise
        val currentActivity = currentActivity ?: run {
            promise.reject("Activity doesn't exist")
            return
        }

        val mediaProjectionManager = currentActivity.getSystemService(Activity.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        val captureIntent = mediaProjectionManager.createScreenCaptureIntent()
        ActivityCompat.startActivityForResult(currentActivity, captureIntent, SCREENSHOT_REQUEST_CODE, null)
    }

    override fun onActivityResult(activity: Activity?, requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SCREENSHOT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                // Handle the screen capture here
                promise?.resolve("Screen capture success")
            } else {
                promise?.reject("Screen capture failed")
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {}

    companion object {
        private const val SCREENSHOT_REQUEST_CODE = 10001
    }
}
