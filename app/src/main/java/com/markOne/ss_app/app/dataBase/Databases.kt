package com.markOne.ss_app.app.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * RECONSTRUCTED Room databases.
 *
 * Smali evidence:
 *  - MarkDatabase extends Lm2/t (RoomDatabase) with table
 *    "scheduled_screenshot_entity" (MarkDatabase_Impl.e()).
 *  - ScreenshotIndexDatabase extends Lm2/t with table "screenshot_index_entity".
 *  - DAO/entity/column definitions are obfuscated (Lj6/b, Lj6/d, Li6/a, O1/g);
 *    full DAO bytecode is preserved in recovered-smali + recovered-source.
 *
 * Entity shapes below are the MINIMUM consistent reconstruction from UX strings:
 * scheduled timers (path, id, fire-at, status) and search index rows
 * (path/id, OCR text, labels, capture date, dimensions, folder). Column names
 * must be finalized by porting the obfuscated DAO bytecode (see RECOVERY_REPORT
 * "Remaining issues" + documentation/OBFUSCATION_MAP.md).
 */
@Database(
    entities = [ScheduledScreenshotEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class MarkDatabase : RoomDatabase() {
    abstract fun scheduledDao(): ScheduledScreenshotDao
}

@Database(
    entities = [ScreenshotIndexEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class ScreenshotIndexDatabase : RoomDatabase() {
    abstract fun indexDao(): ScreenshotIndexDao
}
