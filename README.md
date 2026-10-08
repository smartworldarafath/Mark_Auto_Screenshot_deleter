# 📸 Mark — Auto Screenshot Deleter (Recovered Source)

> **Mark** is a smart Android screenshot manager that automatically cleans up temporary
> screenshots. Schedule screenshots for deletion, keep the important ones, share & delete,
> and use silent auto-delete mode to reduce gallery clutter and save storage.
>
> Owner-authorized reconstruction of `com.markOne.ss_app` v7.1 (vc 59) from the shipped
> XAPK — maximum source recovery, fully labeled. No UI redesign yet.

## ✨ Features (recovered from the shipped app)

- 👀 **Persistent screenshot watcher** — foreground service monitors the system
  Screenshots folder and reacts instantly to new screenshots
- ⏳ **Delete timers** — *"Keep the Best, Shred the Rest"*: per-screenshot & bulk
  schedule (minutes / hours / days), update or cancel anytime
- 🔍 **Smart search** — on-device OCR index (ML Kit text + image labels)
- 📦 **Scheduled queue + details** — capture date, delete-in countdown, dimensions,
  file name/size/folder
- 📤 **Share & Delete / Share & Keep** with post-share delete timer
- 💎 **Premium / Refill / Remove Ads** via Billing 8.x
- 🎮 **Easter-egg game + walkthrough** (Rookie → Legend ranks)
- 🌍 **Multi-language** — incl. Bengali, Arabic, Hindi, Urdu + many more
- 🔔 **Auto-start on boot** + 6-hour watchdog worker

## 🛠 Tech stack (pinned from APK evidence)

Kotlin 2.0.21 · Gradle 8.13 · Java 17 · compileSdk/targetSdk 36 · minSdk 26 ·
Jetpack Compose (1.9.0 / Material3 1.3.2) · Hilt 2.57.1 · Room 2.8.3 ·
DataStore 1.1.7 · WorkManager 2.10.3 · Navigation-Compose 2.9.3 ·
Coroutines 1.10.2 · Billing-KTX 8.0.0 · AdMob + Meta Audience Network ·
ML Kit Text 16.0.1 + Image-Labeling 17.0.8 · Firebase Analytics/Crashlytics ·
UMP Consent 3.0 · In-App Review · Play Licensing wrapper (`com.pairip`)

## 📁 Repository layout

```
app/                      Buildable Android scaffold (Gradle Kotlin DSL)
  src/main/
    java/com/markOne/...  Reconstructed Kotlin entry points (labeled per file)
    res/                  Recovered original resources (187 files, names kept)
    assets/               Recovered assets (Meta secondary dex + ML Kit models)
    jniLibs/arm64-v8a/    Recovered native libs (4 .so)
    AndroidManifest.xml   Reconstructed manifest (verbatim components)
  google-services.json.PLACEHOLDER   Restore from Firebase console (see below)
documentation/            Strings/values dumps, smali dumps, evidence scans,
                          obfuscation map, res/asset inventories
third-party/              Pinned dependency versions + META-INF evidence
RECOVERY_REPORT.md        Full recovery report (build status + remaining issues)
RECOVERED_COMPONENTS.md   Everything successfully recovered
UNRECOVERABLE_COMPONENTS.md  What cannot come back from an XAPK
```

> 🗄️ **Heavy evidence archives** (12,002 smali files · 11,582 JADX sources ·
> original `classes.dex` · native libs · split APKs) ship as **GitHub Release
> assets** to keep the repo clean and fast.

## 🚀 Build

1. Install **Android SDK 36 + JDK 17**.
2. Restore `app/google-services.json` from Firebase console project
   `playstore-apps-8ca79` (placeholder ships here; **rotate exposed keys**).
3. Sync Gradle and run `:app:assembleDebug`.
4. Remaining work: `RECOVERY_REPORT.md` → *Remaining issues*.

## 🔒 Provenance & labeling

- **Recovered** = byte-identical evidence · **Reconstructed** = behavior-equivalent
  Kotlin ported from smali · **Generated** = build scaffold · **Not recoverable** =
  original names/comments, signing keys, `google-services.json`, server content.
- Original decompiled material is preserved untouched alongside the scaffold.

