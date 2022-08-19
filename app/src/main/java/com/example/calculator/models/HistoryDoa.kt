package com.example.calculator.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calculator.schemas.History

@Dao
interface HistoryDoa {
    @Query("SELECT * FROM HISTORY")
    suspend fun getAll():List<History>

    @Insert
    suspend fun addHistory(hist:History)

}