package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.ErrorMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.*
import java.net.HttpURLConnection

@Serializable
class Response(val action: String, val key: String, val data: String, val message: String, val status: Int) {

    companion object {

        fun ok(action: String, key: String, data: String, message: String = ErrorMessage.OK): Response {
            return Response(action, key, data, message, HttpURLConnection.HTTP_OK)
        }

        fun error(action: String, key: String, data: String, message: String?, status: Int = HttpURLConnection.HTTP_BAD_REQUEST): Response {
            return Response(action, key, data, ErrorMessage.check(message), status)
        }

    }

    fun toJSON(): String {
        return Json.encodeToString(this)
    }

}
