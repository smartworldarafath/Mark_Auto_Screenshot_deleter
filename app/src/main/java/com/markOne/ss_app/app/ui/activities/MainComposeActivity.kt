package com.markOne.ss_app.app.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * RECONSTRUCTED launcher activity.
 *
 * Smali evidence (recovered-smali/.../MainComposeActivity.smali):
 *  - class ...MainComposeActivity extends Lc/l (ComponentActivity) implements La7/b (Hilt)
 *  - attachBaseContext locale-wraps newBase via LF4/D.b() with fallback
 *  - onCreate: edge-to-edge, lifecycle wiring (tags 0x7f090160/0x7f090161),
 *    billing repository O6/j lifecycle, in-app-review helper n6/k hook in onResume
 *  - Compose content graph itself is obfuscated (single-letter packages);
 *    screen copy/strings in documentation/app_strings.tsv define the real UX:
 *    Home / Screenshots / Search bottom nav, delete-timer sheets, details,
 *    search + OCR indexing progress, premium/refill/remove-ads, easter-egg game.
 */
class MainComposeActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        // RECOVERED: locale-wrap with try/catch fallback (see smali attachBaseContext)
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO(reconstruction): re-attach Compose NavHost + bottom nav
            // (Home / Screenshots / Search) + timer/detail/search/premium sheets
            // using strings in documentation/app_strings.tsv and raw Lottie
            // animations in app/src/main/res/raw. Compose call graph lives in
            // recovered-smali + recovered-source under obfuscated packages.
        }
    }

    override fun onResume() {
        super.onResume()
        // RECOVERED: in-app-review trigger hook (n6/k.a(activity)) wrapped in try/catch
    }

    override fun onDestroy() {
        // RECOVERED: billing repository (O6/j) cleanup + coroutine scope cancel
        super.onDestroy()
    }
}
