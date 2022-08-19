package com.example.calculator.Databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calculator.models.HistoryDoa
import com.example.calculator.schemas.History

@Database(entities = [History::class], version = 1)
abstract class HistoryDB : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDoa

    companion object {
        var INSTANCE: HistoryDB? = null

        fun getHistoryDB(context: Context): HistoryDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDB::class.java,
                    "historyDb"
                ).build()
            }
            return INSTANCE as HistoryDB
        }

        fun cleanDB() {
            INSTANCE = null
        }

    }

}