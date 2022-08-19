package com.example.calculator.schemas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "expression") var expression: String,
    @ColumnInfo(name = "result") var result: String
)