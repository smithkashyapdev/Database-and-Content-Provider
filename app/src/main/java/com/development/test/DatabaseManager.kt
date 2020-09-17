package com.development.test

import android.content.Context
import android.os.Environment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.development.test.models.Transaction
import java.io.File

@Database(
    entities = [Transaction::class],
    version = 1
)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun getTransactionDao(): TransactionDao

    companion object {

        @Volatile
        private var instance: DatabaseManager? = null
        private val LOCK = Any()
        var baseDir = Environment.getExternalStorageDirectory().absolutePath
        var fileName = "MyDb.db"
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =


            Room.databaseBuilder(
                context.applicationContext,
                DatabaseManager::class.java,
                fileName
            ).allowMainThreadQueries()
                .build()
    }



}