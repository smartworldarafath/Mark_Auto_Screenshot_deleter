# Unrecoverable / must-restore-manually — Mark 7.1

1. Original Kotlin sources, comments, Compose screen graphs, ViewModels,
   navigation routes, resource (non-string) comments — minified away; only
   JADX/smali equivalents survive.
2. Original method/field/class names for obfuscated packages (A/…Z7, p*/…);
   mapping must be rebuilt by behavior, never guessed.
3. `google-services.json` — not shipped in APK; placeholder only. Restore from
   Firebase console project playstore-apps-8ca79.
4. Firebase/API secrets (api key, app id, sender id, db url, bucket, web client
   id, AdMob app id value is public in manifest) — redacted here; rotate and
   re-enter from console.
5. Signing keystore + passwords — required for updates; not in XAPK.
6. ProGuard/R8 mapping.txt — not shipped; stack traces cannot be de-obfuscated.
7. Other ABIs/densities/languages (armeabi-v7a, x86_64, non-en/non-xxhdpi) —
   only arm64_v8a + xxhdpi + en splits were provided.
8. Room DAO/entity column details beyond table names — live in obfuscated
   bytecode; scaffold entities are minimum-consistent, finalize by porting.
9. Remote content: Play listing assets, Firebase Remote Config values, ad unit
   IDs beyond the app id, server data — not in XAPK.
10. Build history/CI scripts/flavors beyond Gradle 8.13 + Kotlin 2.0.21 evidence.
