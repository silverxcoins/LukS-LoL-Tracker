package ru.mobile.lukslol.service.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mobile.lukslol.service.db.dao.SummonerDao
import ru.mobile.lukslol.service.db.entity.DbSummoner

@Database(entities = [DbSummoner::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun summonerDao(): SummonerDao
}