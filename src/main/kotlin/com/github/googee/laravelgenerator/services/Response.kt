package com.github.googee.laravelgenerator.services

import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
class Response(val status: Int, val key: String, val data: String, val message: String) {

    companion object {

        fun ok(key: String, data: String, message: String = "OK"): Response {
            return Response(200, key, data, message)
        }

        fun error(key: String, data: String, message: String): Response {
            return Response(400, key, data, message)
        }

    }

    fun toJSON(): String {
        return Json.encodeToString(this)
    }

}
