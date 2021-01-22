package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.ErrorMessage
import com.github.googee.laravelgenerator.services.ResponseStatus
import kotlinx.serialization.json.Json
import kotlinx.serialization.*

@Serializable
class Response(val action: String, val key: String, val data: String, val message: String, val status: Int) {

    companion object {

        fun ok(action: String, key: String, data: String, message: String = ErrorMessage.OK): Response {
            return Response(action, key, data, message, ResponseStatus.OK)
        }

        fun error(action: String, key: String, data: String, message: String?): Response {
            val text = message ?: ErrorMessage.Unknown
            return Response(action, key, data, text, ResponseStatus.Error)
        }

    }

    fun toJSON(): String {
        return Json.encodeToString(this)
    }

}
