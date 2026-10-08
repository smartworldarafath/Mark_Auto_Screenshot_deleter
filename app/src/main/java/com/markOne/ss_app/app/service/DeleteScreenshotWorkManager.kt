package com.markOne.ss_app.app.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * RECONSTRUCTED scheduled-deletion worker (1095-line smali).
 *
 * Input: screenshot_path (String), screenshot_id (Long, default -1).
 * doWork flow:
 *  - deletes via MediaStore/DocumentFile (_display_name / document_id handling,
 *    "Failed query: " logging); on failure logs screenshot_deleted_failed
 *  - restarts ScreenshotWatcherForegroundService if not running
 *    (events: foreground_service_running_ss_worker /
 *    foreground_service_restart_from_ss_worker)
 *  - errors: "DeleteScreenshotWorkManager: Failed to start service - <msg>",
 *    "DeleteScreenshotWorkManager: Exception while deleting file at <path> - <msg>"
 */
@HiltWorker
class DeleteScreenshotWorkManager @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
) : Worker(appContext, params) {

    override fun doWork(): Result {
        val path = inputData.getString(KEY_PATH).orEmpty()
        val id = inputData.getLong(KEY_ID, -1L)
        // TODO(reconstruction): port MediaStore/DocumentFile deletion + FGS restart.
        return Result.success()
    }

    companion object {
        const val KEY_PATH = "screenshot_path" // RECOVERED input key
        const val KEY_ID = "screenshot_id"     // RECOVERED input key
        const val UNIQUE_PREFIX = "delete_screenshot_"
    }
}
