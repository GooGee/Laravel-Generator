package com.github.googee.laravelgenerator.services.json

import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
class JSRequest(val key: String, val data: String) {

    companion object {

        fun load(text: String): JSRequest {
            return Json.decodeFromString(JSRequest.serializer(), text)
        }

    }

}
