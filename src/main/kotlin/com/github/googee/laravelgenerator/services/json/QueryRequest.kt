package com.github.googee.laravelgenerator.services.json

import kotlinx.serialization.json.Json

class QueryRequest(text: String) {
    val route: String
    val data: String

    init {
        val json = Json.decodeFromString(JSRequest.serializer(), text)
        route = json.key
        data = json.data
    }

}
