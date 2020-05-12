package ru.mobile.lukslol.service.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.mobile.lukslol.service.db.converter.PostDataConverter
import ru.mobile.lukslol.service.db.converter.SummonerConverter
import java.util.*

@Entity
@TypeConverters(SummonerConverter::class, PostDataConverter::class)
data class DbPost(
    @PrimaryKey
    val id: String,
    val puuid: String,
    val title: String,
    val date: Calendar,
    val summoner: DbSummoner?,
    val data: DbPostData
)