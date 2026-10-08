# Mark recovery — README

Owner-authorized reconstruction of **Mark** (`com.markOne.ss_app`, v7.1 / vc 59)
from `Screenshot app.xapk`. No UI redesign was done; functionality preserved.

## Layout
- `app/` — buildable Android scaffold (Gradle Kotlin DSL, compileSdk 36).
  Resources/assets/jniLibs are the **recovered originals**; Kotlin sources are
  **reconstructed equivalents** documented per-file. Secrets are placeholders.
- `recovered-source/` — full JADX output (11,582 .java + decoded resources).
- `recovered-smali/` — full apktool smali (12,002 files, authoritative for logic).
- `recovered-resources/` — decoded res/, AndroidManifest.xml, apktool.yml, unknown/.
- `recovered-assets/` — assets/ incl. audience_network.dex + ML Kit OCR models.
- `recovered-native/` — arm64-v8a .so files from config.arm64_v8a.apk.
- `recovered-dex/` — original classes.dex (9,674,488 bytes, DEX 038, 12,002 classes).
- `original-xapk-analysis/` — split APKs, manifest.json, class list, hashes.
- `third-party/` — pinned dependency versions + META-INF evidence.
- `documentation/` — strings, values dumps, smali dumps, evidence scans, map.

## Build
1. Install Android SDK 36 + JDK 17.
2. Restore `app/google-services.json` from Firebase console project
   `playstore-apps-8ca79` (only a placeholder ships here; keys are redacted).
3. Sync Gradle (Gradle 8.13, AGP 8.7.3, Kotlin 2.0.21) and rebuild.
4. Remaining work is listed in Others_REPORT.md ("Remaining issues" + "Build status").

## Rules followed
Recovered vs reconstructed vs generated vs unrecoverable are labeled in every
file header and in Others_REPORT.md / Others_COMPONENTS.md /
Extra_COMPONENTS.md. Nothing was silently invented.
