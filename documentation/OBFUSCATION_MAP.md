# Obfuscation map — Mark (com.markOne.ss_app) 7.1

The shipped DEX was minified with R8 (single-letter root packages A/, B/,
C/..., flattened names, `SourceFile` only). Package names of the 12 readable
classes survived; everything else (methods/fields/classes) is renamed.

## Readable entry points (keep as-is, see app/proguard-rules.pro)
- `Lcom/markOne/AppApplication;` -> Application subclass (DI/l10n/analytics/boot work)
- `Lcom/markOne/ss_app/app/ui/activities/MainComposeActivity;` -> launcher ComponentActivity (Lc/l)
- `Lcom/markOne/ss_app/app/service/ScreenshotWatcherForegroundService;` -> FGS Service
- `Lcom/markOne/ss_app/app/service/BootCompletedReceiver;` -> BOOT receiver
- `Lcom/markOne/ss_app/app/service/DeleteScreenshotWorkManager;` -> Worker (screenshot_id/path)
- `Lcom/markOne/ss_app/app/service/ScreenshotIndexWorker;` -> CoroutineWorker (OCR index)
- `Lcom/markOne/ss_app/app/service/dailyServiceChecker/DailyPeriodicWorkScheduler;` -> CoroutineWorker watchdog
- `Lcom/markOne/ss_app/app/dataBase/MarkDatabase;` + `_Impl` -> Room DB, table scheduled_screenshot_entity
- `Lcom/markOne/ss_app/app/dataBase/ScreenshotIndexDatabase;` + `_Impl` -> Room DB, table screenshot_index_entity
- `Lcom/pairip/application/Application;` -> manifest application wrapper (licensing SDK)
- `Lcom/pairip/licensecheck/*;` (35 classes) -> licensing wrapper (LicenseActivity/Client/TrialClient)

## Known obfuscated collaborators (do NOT guess names)
| Smali ref | Role (evidence) |
|---|---|
| `LT6/b;`, `LT6/a;` | prefs/DataStore holder (`c()`, `a()`, `e()`, `f()`, `b()`) |
| `LO6/a;`, `LO6/j;` | common state holder / billing repository |
| `Ln6/e;`, `Ln6/b;`, `Ln6/k;`, `Ln6/l;` | consent manager, analytics wrapper, review helper, worker continuation |
| `Lp6/a;`, `Lp6/b;` | analytics facade (`b()`, `c()`, `e()`, `f()`) |
| `LF4/D;` | locale wrapper (`b(context)`) |
| `LS6/b;`, `LS6/c;` | language table (`r(string)`, SYSTEM_DEFAULT) |
| `LG2/F,G,q,j,u,s,w;` | WorkManager request/params/result types |
| `Lj6/b;`, `Lj6/d;`, `Lj6/a;`, `Li6/a;`, `LO1/g;`, `Lm2/t,h;` | Room runtime/DAO impl types |
| `Lc/l;`, `La7/b;` | ComponentActivity / Hilt entry-point marker |
| `LD7/*`, `LI7/*`, `LE7/*`, `LF4/*`, `LH4/*`, `LG2/*` | coroutines/dispatchers/lifecycle helpers |

## Preserved representations
- Full smali: `recovered-smali/smali/` (12,002 files).
- Full JADX: `recovered-source/sources/` (11,582 files) + `resources/`.
- DEX class list: `original-xapk-analysis/classes_list.txt` (12,002).
- Do not delete obfuscated trees; reconstruction must map, never rename blindly.
