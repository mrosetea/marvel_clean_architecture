package com.example.marvelcleanarchitectureapp.modules.home.data.api.model

import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character

class CharactersResponse (
    val code: Int? = 0,
    val status: String? = "",
    val copyright: String? = "",
    val data: Data? = null,
    val attributionText: String? = "",
    val attributionHTML: String? = "",
    val etag: String? = ""
) {
    class Data(
        val offset: Int? = 0,
        val limit: Int? = 0,
        val total: Int? = 0,
        val count: Int? = 0,
        val results: List<Result>? = null
    ) {
        class Result(
            val id: Int? = 0,
            val name: String? = "",
            val description: String? = "",
            val modified: String? = "",
            val thumbnail: Thumbnail? = null,
            val resourceURI: String? = null,
            val comics: Comics? = null,
            val series: Series? = null,
            val stories: Stories? = null,
            val events: Events? = null,
            val urls: List<Url>? = null,
            ) {
            class Thumbnail(
                val path: String? = "",
                val extension: String? = ""
            )
            class Comics(
                val available: Int? = 0,
                val collectionURI: String? = "",
                val items: List<ComicItem>
            ) {
                class ComicItem(
                    val resourceURI: String? = "",
                    val name: String? = ""
                )
            }
            class Series(
                val available: Int? = 0,
                val collectionURI: String? = "",
                val items: List<SerieItem>
            ) {
                class SerieItem(
                    val resourceURI: String? = "",
                    val name: String? = ""
                )
            }
            class Stories(
                val available: Int? = 0,
                val collectionURI: String? = "",
                val items: List<StoryItem>
            ) {
                class StoryItem(
                    val resourceURI: String? = "",
                    val name: String? = ""
                )
            }
            class Events(
                val available: Int? = 0,
                val collectionURI: String? = "",
                val items: List<EventItem>
            ) {
                class EventItem(
                    val resourceURI: String? = "",
                    val name: String? = ""
                )
            }
            class Url(
                val type: String? = "",
                val url: String? = ""
            )
        }
    }
}

fun CharactersResponse.Data.Result.toCharacter (): Character {
    return Character(id ?: 0, name.orEmpty(), thumbnail?.path + "." + thumbnail?.extension)
}