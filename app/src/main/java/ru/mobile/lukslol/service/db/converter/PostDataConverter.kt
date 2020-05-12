package ru.mobile.lukslol.service.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonObject
import ru.mobile.lukslol.service.db.entity.DbPostData
import ru.mobile.lukslol.service.db.entity.DbPostData.*
import java.lang.Exception

class PostDataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromPostData(postData: DbPostData): String {
        return gson.toJson(postData)
    }

    @TypeConverter
    fun toPostData(json: String): DbPostData {
        val jsonObject = try {
            gson.fromJson(json, JsonObject::class.java)
        } catch (e: Exception) {
            return Unknown()
        }
        return when (jsonObject["type"].asString) {
            Custom.TYPE_NAME -> gson.fromJson(jsonObject, Custom::class.java)
            Greeting.TYPE_NAME -> gson.fromJson(jsonObject, Greeting::class.java)
            else -> Unknown()
        }
    }
}