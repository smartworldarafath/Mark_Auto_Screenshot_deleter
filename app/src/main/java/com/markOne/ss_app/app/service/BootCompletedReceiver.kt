package com.markOne.ss_app.app.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

/**
 * RECONSTRUCTED boot receiver (71-line smali, fully recoverable logic).
 * On BOOT_COMPLETED / LOCKED_BOOT_COMPLETED, starts
 * ScreenshotWatcherForegroundService unless already running (static L),
 * then logs analytics event id 7.
 */
class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return
        if (action != Intent.ACTION_BOOT_COMPLETED &&
            action != Intent.ACTION_LOCKED_BOOT_COMPLETED
        ) return
        if (ScreenshotWatcherForegroundService.isRunning) return
        val svc = Intent(context, ScreenshotWatcherForegroundService::class.java)
        ContextCompat.startForegroundService(context, svc)
    }
}
