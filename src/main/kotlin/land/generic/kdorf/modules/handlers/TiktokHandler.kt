package land.generic.kdorf.modules.handlers

import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.event.message.MessageCreateEvent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.JsonObject
import land.generic.kdorf.DorfCommandContext

class TiktokHandler() : MessageHandler {
    override fun canHandle(message: MessageCreateEvent): Boolean {
        return !(message.member?.isBot ?: true) && message.message.content.startsWith("https://www.tiktok.com/")
    }

    override suspend fun handle(message: MessageCreateEvent) {
        val response: HttpResponse = HttpClient() {
            install(JsonFeature)
        }.request("https://www.tiktok.com/oembed") {
            parameter("url", message.message.content)
        }
        try {
            val string:String = response.receive()
            val format = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            val obj = format.decodeFromString<JsonObject>(string)

            message.message.channel.createEmbed {
                title = obj["title"].toString().removeSurrounding("\"").ifEmpty { "Lol this video has no title." }
                url = message.message.content
                this.thumbnail{
                    url = obj["thumbnail_url"].toString().removeSurrounding("\"")
                }
            }
        }catch (ex:Exception){
            println(ex)
        }
    }


}

@Serializable
data class TikTokEmbed(
    val title: String,
    val thumbnail_url: String
)