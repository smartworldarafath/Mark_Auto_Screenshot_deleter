# RECONSTRUCTED ProGuard rules. The shipped APK was minified with R8
# (evidence: single-letter packages A/, B/, C/..., flattened obfuscation,
# MissingObfuscationMapping-style renames in JADX output).
# Keep every recovered entry point by its exact recovered name so
# workers/services/receivers keep working after a rebuild.
-keep class com.markOne.AppApplication { *; }
-keep class com.markOne.ss_app.app.ui.activities.MainComposeActivity { *; }
-keep class com.markOne.ss_app.app.service.ScreenshotWatcherForegroundService { *; }
-keep class com.markOne.ss_app.app.service.BootCompletedReceiver { *; }
-keep class com.markOne.ss_app.app.service.DeleteScreenshotWorkManager { *; }
-keep class com.markOne.ss_app.app.service.ScreenshotIndexWorker { *; }
-keep class com.markOne.ss_app.app.service.dailyServiceChecker.DailyPeriodicWorkScheduler { *; }
-keep class com.markOne.ss_app.app.dataBase.** { *; }
-keep class com.pairip.** { *; }
# Room / WorkManager / Hilt / Compose runtime needs
-keep class androidx.room.** { *; }
-keep class androidx.work.** { *; }
-keep class androidx.startup.** { *; }
-keep class dagger.hilt.** { *; }
-keep class androidx.compose.** { *; }
-dontwarn com.google.android.gms.internal.**
-dontwarn com.facebook.ads.**
