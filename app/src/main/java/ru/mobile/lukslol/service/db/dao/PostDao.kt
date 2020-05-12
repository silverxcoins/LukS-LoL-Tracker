package ru.mobile.lukslol.service.db.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mobile.lukslol.service.db.entity.DbPost

@Dao
interface PostDao {

    @Query("SELECT * FROM DbPost WHERE puuid = :summonerPuuid ORDER BY id LIMIT :limit OFFSET :offset")
    fun getPosts(summonerPuuid: String, limit: Int, offset: Int): List<DbPost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<DbPost>)

    @Query("DELETE FROM DbPost WHERE puuid = :summonerPuuid")
    fun deleteAll(summonerPuuid: String)
}