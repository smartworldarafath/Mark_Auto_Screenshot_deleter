// Top-level build file for the Mark (com.markOne.ss_app) recovery scaffold.
// See RECOVERY_REPORT.md: versions below are pinned from META-INF/*.version files
// recovered from the shipped base APK. AGP/Kotlin/Gradle versions are modern
// equivalents capable of building compileSdk 36; the ORIGINAL build used
// Gradle 8.13 + Kotlin 2.0.21 (see kotlin-tooling-metadata.json in
// original-xapk-analysis / documentation).
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
}
