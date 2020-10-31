package com.github.googee.laravelgenerator.services.json

import kotlinx.serialization.json.Json

class FileRequest(text: String) {
    val file: String
    val data: String

    init {
        val json = Json.decodeFromString(JSRequest.serializer(), text)
        file = json.key
        data = json.data
    }

}
