package com.markOne

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * RECONSTRUCTED Application class.
 *
 * Smali evidence (recovered-smali/smali/com/markOne/AppApplication.smali and
 * recovered-source/sources/com/markOne/AppApplication.java):
 *  - class com.markOne.AppApplication extends android.app.Application
 *  - attachBaseContext wraps base via LF4/D.b() (locale helper) with fallback
 *  - onCreate: Hilt-style lazy graph init; reads "system" language pref from
 *    T6/b (SharedPreferences/DataStore holder); applies locale; logs
 *    "Applied language on start: "; inits consent (n6/e), analytics (p6/a),
 *    common state holder (O6/a); first-launch grace windows:
 *    +40d no-ads date, +10d review/free-till date; enqueues unique periodic
 *    work "daily_service_checker_worker_1" every 6h via DailyPeriodicWorkScheduler.
 *
 * Obfuscated collaborators (T6/b, O6/a, n6/e, p6/a, S6/c, G2/F...) live in
 * recovered-smali / recovered-source under single-letter packages and are NOT
 * renamed here — see documentation/OBFUSCATION_MAP.md. Wire them back by
 * matching the field/method shapes documented in RECOVERY_REPORT.md.
 */
@HiltAndroidApp
class AppApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun attachBaseContext(base: Context) {
        // RECOVERED behavior: locale-wrap with try/catch fallback to raw base.
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        // RECONSTRUCTED order (from smali onCreate):
        // 1. init DI graph once (guard t flag)
        // 2. apply stored language or system locale
        // 3. init consent + analytics (incl. system_language_non_english event)
        // 4. seed first-launch no-ads/review timestamps
        // 5. enqueue DailyPeriodicWorkScheduler every 6h (unique: daily_service_checker_worker_1)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
