package com.example.database.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.network.data.Vocation
import com.example.network.data.Vocations

@Entity(tableName = "vacanciesTable")
class VacanciesEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "vacancy")
    val vacancy: Vocation
)