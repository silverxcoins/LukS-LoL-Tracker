package ru.mobile.lukslol.service.db.dao

import androidx.room.*
import ru.mobile.lukslol.service.db.entity.DbSummoner

@Dao
interface SummonerDao {
    @Query("SELECT * FROM DbSummoner WHERE id = :id")
    fun getById(id: String): DbSummoner?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(summoner: DbSummoner)
}