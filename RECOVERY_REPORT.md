# RECOVERY_REPORT.md — Mark (com.markOne.ss_app) 7.1 from Screenshot app.xapk

Owner-authorized recovery. No redesign, no silent invention: every file header
and the lists below label **recovered** (byte-identical evidence), **reconstructed**
(behavior-equivalent from smali/JADX), **generated** (scaffold), and **not recoverable**.

## Application
- package: `com.markOne.ss_app` | app name: **Mark** | versionName **7.1** | versionCode **59**
- minSdk **26**, targetSdk **36**, compileSdk **36** (platform 16)
- framework: **native Android Kotlin + Jetpack Compose** (Compose BOM-era 1.9.0
  artifacts, ComponentActivity setContent, no app XML layouts; 26 layouts are SDK/ads)
- Kotlin **2.0.21**, Gradle **8.13**, Java **17** (kotlin-tooling-metadata.json)
- git rev `4a95ecf974c14fa0162af1748aa5fc37993a90f7`; Crashlytics mapping
  `5aefa36a29384b61b03e37bb04097db9`
- splits: base + config.en + config.xxhdpi + config.arm64_v8a (Play App Bundle)
- entry: `MainComposeActivity` (portrait, launcher); app class
  `com.pairip.application.Application` wrapping `com.markOne.AppApplication`
- what it is: screenshot manager — persistent FGS watches MediaStore Screenshots,
  prompts timed auto-delete ("Keep the Best, Shred the Rest"), schedules WorkManager
  deletions, OCR-indexes for search (ML Kit text+labels), bulk timer/share, details,
  premium/refill/remove-ads via Billing 8.x, AdMob + Meta ads, Firebase analytics/
  crashlytics, UMP consent, in-app review, boot auto-start, 6-hour watchdog.

## Recovered (byte-identical evidence preserved)
- XAPK splits + manifest.json (original-xapk-analysis/split-apks, INPUT_HASHES.txt)
- classes.dex 9,674,488 B DEX 038: **12,002 classes / 44,217 strings / 13,755 types /
  13,059 protos / 45,106 fields / 56,436 methods** (recovered-dex + classes_list.txt)
- Full smali 12,002 files (recovered-smali) + full JADX 11,582 .java (recovered-source)
- Decoded manifest + res/ (187) + assets/ (25) + unknown/ (recovered-resources)
- 4 arm64-v8a .so (recovered-native): path, datastore counter, 2 ML Kit pipelines
- All permissions/components/authorities/intent-filters/FGS text/AdMob id/billing meta
- 268 strings, colors, dimens, styles (AppTheme), arrays, ids, file/backup/xml configs
- 16 Lottie animations, vectors, webp icons, OCR models, audience_network.dex
- 97 dependency version pins, licenses, kotlin tooling metadata, stamp cert hash

## Reconstructed (behavior-equivalent, labeled in code)
- app/ scaffold: Gradle KTS (pinned deps), manifest (verbatim components),
  ProGuard keeps, res/assets/jniLibs (originals), 8 Kotlin entry points ported
  from smali control-flow/strings (Application, Activity, FGS, Boot receiver,
  3 workers, Room DBs + minimum entities/DAOs).
- Room table names + worker keys/tags + channels + FGS text + schedules are exact;
  DAO columns/obfuscated collaborators are minimum-consistent (see Remaining issues).

## Partially recovered
- Compose screen graphs/navigation/ViewModels/state holders/billing/consent/review/
  analytics/OCR call bodies: control flow + strings preserved in smali/JADX, names
  lost; needs method-by-method porting (evidence_*.txt document call sites).
- Room DAO/entity columns (table names exact; columns minimum-consistent).
- Hilt graph (modules/bindings inferred from usage, not annotations).
- Ad unit IDs beyond the manifest app id; review/consent runtime tuning.

## Not recoverable (see UNRECOVERABLE_COMPONENTS.md)
Original sources/comments, obfuscated names, google-services.json, secrets,
keystore, mapping.txt, non-shipped splits/locales, remote/server content.

## Obfuscation
R8 full-mode: 12 readable classes keep names; ~11,990 classes flattened into
A–Z/a–z + pNNN roots. Method/field names lost (t–z, a()…); `SourceFile` only.
Impact: logic fully analyzable in smali; re-porting must follow
documentation/OBFUSCATION_MAP.md. Original smali/JADX never modified.

## Dependencies (pinned, evidence: third-party/dependency_versions.tsv)
Compose BOM 2025.04 (1.9.0 / m3 1.3.2 / icons 1.7.8), activity 1.10.1,
lifecycle 2.9.4, navigation 2.9.3, room 2.8.3, sqlite 2.6.1, datastore 1.1.7,
work 2.10.3, hilt 2.57.1, coroutines 1.10.2, billing-ktx 8.0.0,
play-services-ads 24.x + facebook mediation 6.18, ML Kit text 16.0.1 + labels
17.0.8, firebase-bom 33.7 + UMP 3.0, browser 1.8, constraintlayout 2.2.1,
webkit 1.14, appcompat 1.7.1, emoji2 1.4, startup 1.1.1, profileinstaller 1.4.
Native: androidx.graphics.path, datastore counter, mlkitcommonpipeline,
mlkit_google_ocr_pipeline (all arm64-v8a ELF AArch64, clang 14.0.7).
audience_network.dex 5,017,908 B (dex 035, 3,235 classes) preserved as asset.

## Firebase/Google config references (values REDACTED here — restore from console)
Project playstore-apps-8ca79; sender 832322979395; app id
1:832322979395:android:d4126a54dfb4b11a91f6f6; rtdb
https://playstore-apps-8ca79-default-rtdb.firebaseio.com; bucket
playstore-apps-8ca79.firebasestorage.app. AdMob app id (public, in manifest):
ca-app-pub-4177515896960908~2773504436. Rotate exposed keys.

## Deep links / intent filters / permissions / metadata
Launcher MAIN; BOOT_COMPLETED + LOCKED_BOOT_COMPLETED; FileProvider
(com.markOne.ss_app.fileprovider, external All + image_provider/);
providers mlkitinitprovider/AudienceNetwork/mobileadsinitprovider/
firebaseinitprovider/androidx-startup; billing proxies; GMS/MLKit/Firebase
registrars; splits/stamp meta. Full 21-permission list in manifest section.

## Feature flags / configuration
Backup rules (exclude app_preferences.xml, keep retain_*.xml); WorkManager +
EmojiCompat + ProcessLifecycle + ProfileInstaller initializers; ads optimize
flags; FGS specialUse subtype (screenshot monitoring justification);
6h watchdog (daily_service_checker_worker_1); in-app review/billing/consent
hooks; OCR bundling flags; baseline profiles.

## Build status
Scaffold assembled with recovered res/assets/jniLibs + pinned deps; Gradle
sync/build NOT yet executed in this session (no Gradle distribution on PATH;
SDK 34–37 present at C:\Users\HP Omnibook X Flip\AppData\Local\Android\Sdk;
JDK 21 available). To verify: install Gradle 8.13 (wrapper configured),
restore google-services.json, run `:app:assembleDebug`. Known blockers before
green build: google-services.json placeholder, Hilt KSP wiring of obfuscated
graph, Room schema finalization from DAO bytecode, Compose screen re-attachment.

## Remaining issues
1. Port obfuscated Compose/UI/billing/consent/review/analytics/OCR bodies.
2. Finalize Room entities/DAOs/migrations from DAO bytecode.
3. Restore google-services.json + rotate secrets + real signing config.
4. Re-add non-shipped splits if multi-ABI/multi-locale support needed.
5. Re-verify FGS/notification/overlay/MediaStore behavior on Android 14+ (36).
6. Full Gradle build + lint + bundle verification pending (see Build status).
7. No redesign done per instructions — UI work is a separate later phase.
