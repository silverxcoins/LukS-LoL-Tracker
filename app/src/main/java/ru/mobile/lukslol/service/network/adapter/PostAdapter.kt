package ru.mobile.lukslol.service.network.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.mobile.lukslol.service.network.dto.NetworkPost
import ru.mobile.lukslol.service.network.dto.NetworkPost.*
import ru.mobile.lukslol.util.parse
import java.lang.Exception
import java.lang.reflect.Type

class PostAdapter : JsonDeserializer<NetworkPost> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): NetworkPost {
        val jsonObject = json.asJsonObject
        val id = jsonObject["postId"].asString
        val puuid = jsonObject["puuid"].asString
        val title = jsonObject["title"].asString
        val date = jsonObject["dateTime"].asString
        val type = jsonObject["postType"].asString
        val dataElement = jsonObject["postData"]
        val data = try {
            when (type) {
                PostData.Custom.serverName -> context.parse<PostData.Custom>(dataElement)
                PostData.Greeting.serverName -> context.parse<PostData.Greeting>(dataElement)
                else -> PostData.Unknown
            }
        } catch (e: Exception) {
            PostData.Unknown
        }

        return NetworkPost(id, puuid, title, date, type, data)
    }
}
