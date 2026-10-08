package com.markOne.ss_app.app.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ContentResolver
import android.content.Intent
import android.database.ContentObserver
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.provider.MediaStore

/**
 * RECONSTRUCTED foreground screenshot watcher.
 *
 * Smali evidence (ScreenshotWatcherForegroundService.smali, 1032 lines):
 *  - android.app.Service, specialUse FGS (manifest property text preserved)
 *  - registers ContentObserver on MediaStore.Images.Media.EXTERNAL_CONTENT_URI
 *    on a "ShotWatch" HandlerThread; static flags L (running) / M; last path N
 *  - posts notification channel "Mark" (screenshot_watcher_service) + relaunch
 *    channel on unexpected stop; cancels id 2 on restart path
 *  - overlay WindowManager fields (A/K) + safelyRemoveView logging
 *  - triggers delete-timer prompt flow: "[Mark] This screenshot will be deleted in %1$s."
 *  - watch path: reads stored screenshot folder pref, registers observer,
 *    dispatches new screenshots into DeleteScreenshotWorkManager + ScreenshotIndexWorker
 */
class ScreenshotWatcherForegroundService : Service() {

    companion object {
        @Volatile var isRunning: Boolean = false // RECOVERED static L
        @Volatile var flagM: Boolean = false     // RECOVERED static M
        @Volatile var lastPath: String = ""      // RECOVERED static N
        const val CHANNEL_WATCHER = "screenshot_watcher_service"
        const val CHANNEL_RELAUNCH = "foreground_service"
    }

    private var observer: ContentObserver? = null
    private var shotThread: HandlerThread? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        ensureChannel(
            CHANNEL_WATCHER,
            getString(com.markOne.ss_app.R.string.notification_channel_screenshot_watcher),
            getString(com.markOne.ss_app.R.string.notification_channel_screenshot_watcher_description),
        )
        startForeground(1, buildNotification())
        registerShotObserver()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        unregisterShotObserver()
        isRunning = false
        // RECOVERED: relaunch notification path ("Mark - Background Service Stopped")
        super.onDestroy()
    }

    private fun registerShotObserver() {
        val thread = HandlerThread("ShotWatch").also { it.start() }
        shotThread = thread
        val obs = object : ContentObserver(Handler(thread.looper)) {
            override fun onChange(selfChange: Boolean) {
                // TODO(reconstruction): query MediaStore for newest screenshot,
                // dedupe via lastPath, enqueue DeleteScreenshotWorkManager with
                // (screenshot_path, screenshot_id) + ScreenshotIndexWorker.
            }
        }
        observer = obs
        contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, obs,
        )
    }

    private fun unregisterShotObserver() {
        observer?.let { contentResolver.unregisterContentObserver(it) }
        observer = null
        shotThread?.quitSafely()
        shotThread = null
    }

    private fun ensureChannel(id: String, name: String, desc: String) {
        val nm = getSystemService(NotificationManager::class.java) ?: return
        if (nm.getNotificationChannel(id) == null) {
            nm.createNotificationChannel(
                NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW).apply {
                    description = desc
                },
            )
        }
    }

    private fun buildNotification(): Notification {
        // TODO(reconstruction): replicate recovered notification layout/copy
        // (screenshot_watcher_service channel id "Mark", ongoing FGS notification).
        return Notification.Builder(this, CHANNEL_WATCHER)
            .setContentTitle(getString(com.markOne.ss_app.R.string.notification_channel_screenshot_watcher))
            .setContentText(getString(com.markOne.ss_app.R.string.notification_channel_screenshot_watcher_description))
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .build()
    }
}
