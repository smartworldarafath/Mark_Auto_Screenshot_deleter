package com.markOne.ss_app.app.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

/**
 * RECONSTRUCTED entities/DAOs (see Databases.kt header for evidence + caveat).
 * Table names "scheduled_screenshot_entity" / "screenshot_index_entity" are
 * RECOVERED; columns are the minimum consistent reconstruction.
 */
@Entity(tableName = "scheduled_screenshot_entity")
data class ScheduledScreenshotEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val screenshotPath: String = "",
    val screenshotId: Long = -1L,
    val deleteAtMillis: Long = 0L,
    val createdAtMillis: Long = 0L,
    val status: String = "SCHEDULED",
)

@Dao
interface ScheduledScreenshotDao {
    @Query("SELECT * FROM scheduled_screenshot_entity ORDER BY deleteAtMillis ASC")
    suspend fun allOrdered(): List<ScheduledScreenshotEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ScheduledScreenshotEntity): Long

    @Update
    suspend fun update(item: ScheduledScreenshotEntity)

    @Delete
    suspend fun delete(item: ScheduledScreenshotEntity)

    @Query("DELETE FROM scheduled_screenshot_entity WHERE screenshotPath = :path")
    suspend fun deleteByPath(path: String)
}

@Entity(tableName = "screenshot_index_entity")
data class ScreenshotIndexEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val screenshotPath: String = "",
    val screenshotId: Long = -1L,
    val ocrText: String = "",
    val labels: String = "",
    val captureDateMillis: Long = 0L,
    val width: Int = 0,
    val height: Int = 0,
    val folder: String = "",
    val indexedAtMillis: Long = 0L,
)

@Dao
interface ScreenshotIndexDao {
    @Query("SELECT * FROM screenshot_index_entity ORDER BY captureDateMillis DESC")
    suspend fun allOrdered(): List<ScreenshotIndexEntity>

    @Query("SELECT * FROM screenshot_index_entity WHERE ocrText LIKE '%' || :q || '%' OR labels LIKE '%' || :q || '%' ORDER BY captureDateMillis DESC")
    suspend fun search(q: String): List<ScreenshotIndexEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ScreenshotIndexEntity): Long

    @Query("DELETE FROM screenshot_index_entity WHERE screenshotPath = :path")
    suspend fun deleteByPath(path: String)
}
