package com.observer.apiimplementation.dataLayer.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.observer.apiimplementation.dataLayer.model.ProductData

@Database(entities = [ProductData::class], version = 3, exportSchema = false)
abstract class ProductRoomDatabase : MainDatabase()
{
    companion object
    {
        @Volatile
        private var productDatabase : ProductRoomDatabase? = null

        val migration_1_2 = object : Migration(2,3)
        {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
            CREATE TABLE productDataTable_new (
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                productName TEXT
            )
            """.trimIndent()
                )

                // ✅ Copy data from old table to new table
                db.execSQL(
                    """
            INSERT INTO productDataTable_new (ID, productName)
            SELECT ID, productName FROM productDataTable
            """.trimIndent()
                )

                // ✅ Drop the old table
                db.execSQL("DROP TABLE productDataTable")

                // ✅ Rename new table to old table name
                db.execSQL("ALTER TABLE productDataTable_new RENAME TO productDataTable")

                // ✅ Restore AUTOINCREMENT sequence (Fix duplicate insert issue)
                db.execSQL("UPDATE sqlite_sequence SET seq = (SELECT MAX(ID) FROM productDataTable) WHERE name = 'productDataTable'")
            }
        }

        @Synchronized
        fun initializeDatabase(context: Context) : ProductRoomDatabase?
        {
            productDatabase?.let { return it }

            productDatabase = Room.databaseBuilder(context, ProductRoomDatabase::class.java,"productDatabase").allowMainThreadQueries().addMigrations(migration_1_2).build()
            return productDatabase
        }
    }
}