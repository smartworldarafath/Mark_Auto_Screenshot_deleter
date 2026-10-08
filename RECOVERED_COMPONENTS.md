# Recovered components — Mark 7.1 (com.markOne.ss_app)

## XAPK structure (all preserved in original-xapk-analysis/split-apks/)
- base `com.markOne.ss_app.apk` (15,897,694 B): classes.dex, manifest, res, assets
- `config.xxhdpi.apk` (66,127 B): 45 density PNGs + resources.arsc
- `config.arm64_v8a.apk` (22,131,391 B): 4 arm64-v8a .so + manifest
- `config.en.apk` (49,561 B): English resources.arsc
- `manifest.json`: package/version/SDK/permissions/splits

## DEX/code (12,002 classes, DEX 038, classes.dex 9,674,488 B)
- 11,582 JADX .java in recovered-source/sources + full smali in recovered-smali/smali
- 12 readable app classes: AppApplication, MainComposeActivity,
  ScreenshotWatcherForegroundService, BootCompletedReceiver,
  DeleteScreenshotWorkManager (screenshot_path/screenshot_id),
  ScreenshotIndexWorker, DailyPeriodicWorkScheduler
  (daily_service_checker_worker_1), MarkDatabase(+_Impl,
  scheduled_screenshot_entity), ScreenshotIndexDatabase(+_Impl,
  screenshot_index_entity)
- 35 com.pairip.licensecheck classes (licensing wrapper) + manifest wrapper
  com.pairip.application.Application

## Manifest (verbatim in recovered-resources/AndroidManifest.xml)
- 21 uses-permissions + 1 signature permission; 10 activities (1 app + 9 SDK);
  12 services (1 app FGS specialUse + 11 SDK/WorkManager/Firebase/MLKit);
  11 receivers (1 app boot + 10 WorkManager/System); 6 providers
  (fileprovider, mlkitinitprovider, AudienceNetwork, mobileadsinitprovider,
  firebaseinitprovider, androidx-startup); deep-link/query intents; FGS subtype
  text; AdMob app id; billing 8.0.0 meta; splits meta

## Resources (187 files, names preserved, in app/src/main/res)
- values: strings (268), colors, dimens, styles (AppTheme), arrays, ids,
  integers, public.xml; xml: file_paths, image_share_filepaths, backup_rules,
  data_extraction_rules, splits0; raw: 16 Lottie JSON + firebase keep files;
  drawable: 70 vectors (incl. illustration_storage, smart_search_icon);
  layout: 26 SDK/ad/notification layouts (app UI is Compose); mipmap webp icons

## Assets (25 files, 9,555,301 B)
- audience_network.dex (5,017,908 B, dex 035, 3,235 classes) + dexopt baselines
  + 21 ML Kit OCR model binaries (.binarypb/.tflite/.fb/LabelMap.pb)

## Native (arm64-v8a only, ELF AArch64, in app/src/main/jniLibs)
- libandroidx.graphics.path.so (10,096 B, JNI_OnLoad, PathIterator)
- libdatastore_shared_counter.so (7,112 B, NativeSharedCounter JNI x4)
- libmlkitcommonpipeline.so (10,989,136 B, VisionKit/MediaPipe/TF Lite)
- libmlkit_google_ocr_pipeline.so (11,064,544 B, OCR pipeline)

## Config/metadata
- dependency pins: 97 META-INF .version files (third-party/dependency_versions.tsv)
- Gradle 8.13 + Kotlin 2.0.21 + Java 17 (kotlin-tooling-metadata.json)
- git rev 4a95ecf974c14fa0162af1748aa5fc37993a90f7, Crashlytics mapping id
  5aefa36a29384b61b03e37bb04097db9
