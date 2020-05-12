package ru.mobile.lukslol.service.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.service.db.entity.DbSummoner
import ru.mobile.lukslol.util.tryOrNull

// We need this for saving time-based summoners(not actual)
class SummonerConverter {

    val gson = Gson()

    @TypeConverter
    fun fromSummoner(summoner: DbSummoner?): String {
        return summoner
            ?.let(gson::toJson)
            ?: ""
    }

    @TypeConverter
    fun toSummoner(json: String): DbSummoner? {
        return tryOrNull { gson.fromJson(json, DbSummoner::class.java) }
    }
}