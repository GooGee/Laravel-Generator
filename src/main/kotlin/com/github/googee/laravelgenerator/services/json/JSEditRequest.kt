package com.github.googee.laravelgenerator.services.json

import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
class JSEditRequest(val key: String, val data: String, val type: String) {

    companion object {

        fun load(text: String): JSEditRequest {
            return Json.decodeFromString(serializer(), text)
        }

    }

}
