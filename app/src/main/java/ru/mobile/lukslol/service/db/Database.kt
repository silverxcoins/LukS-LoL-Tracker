package ru.mobile.lukslol.service.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.mobile.lukslol.service.db.converter.CalendarConverter
import ru.mobile.lukslol.service.db.converter.PostDataConverter
import ru.mobile.lukslol.service.db.dao.PostDao
import ru.mobile.lukslol.service.db.dao.SummonerDao
import ru.mobile.lukslol.service.db.entity.DbPost
import ru.mobile.lukslol.service.db.entity.DbSummoner

@Database(entities = [DbSummoner::class, DbPost::class], version = 1)
@TypeConverters(CalendarConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun summonerDao(): SummonerDao

    abstract fun postDao(): PostDao
}