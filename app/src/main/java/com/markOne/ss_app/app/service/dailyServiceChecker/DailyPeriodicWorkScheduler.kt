package com.markOne.ss_app.app.service.dailyServiceChecker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.markOne.ss_app.app.service.ScreenshotWatcherForegroundService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

/**
 * RECONSTRUCTED daily service watchdog (136-line smali, fully recoverable).
 * If the watcher FGS flag is set, logs foreground_service_running_already_managers
 * and succeeds; else restarts the FGS and logs foreground_service/"Manager Restart",
 * on error logs "DailyPeriodicWorkScheduler: Failed to start service - <msg>".
 */
@HiltWorker
class DailyPeriodicWorkScheduler @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            if (!ScreenshotWatcherForegroundService.isRunning) {
                val intent = android.content.Intent(
                    applicationContext,
                    ScreenshotWatcherForegroundService::class.java,
                )
                applicationContext.startForegroundService(intent)
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val UNIQUE_NAME = "daily_service_checker_worker_1" // RECOVERED
        fun schedule(context: Context) {
            val req = PeriodicWorkRequestBuilder<DailyPeriodicWorkScheduler>(6, TimeUnit.HOURS)
                .addTag(UNIQUE_NAME)
                .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                UNIQUE_NAME, ExistingPeriodicWorkPolicy.KEEP, req,
            )
        }
    }
}
