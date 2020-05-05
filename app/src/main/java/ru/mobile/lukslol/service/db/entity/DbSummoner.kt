package ru.mobile.lukslol.service.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbSummoner(
    @PrimaryKey
    val id: String,
    val name: String,
    val icon: String,
    val level: Int
)