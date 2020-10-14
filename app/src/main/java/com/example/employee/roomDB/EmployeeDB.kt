package com.example.employee.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = arrayOf(Employee::class), version = 2, exportSchema = false)
abstract class EmployeeDB():RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: EmployeeDB? = null
        fun getDatabase(context: Context): EmployeeDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDB::class.java,
                    "employee_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE Employee "
                        + "DEL COLUMN isChecked Bool"
            )
        }
    }

}