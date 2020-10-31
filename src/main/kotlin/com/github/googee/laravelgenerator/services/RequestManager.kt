package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.json.JSRequest
import java.net.HttpURLConnection

class RequestManager {

    companion object {
        const val ErrorMessage = "Error"
        const val ErrorMessageParse = "Error parsing JSON data"

        fun get(text: String): Response {
            var json: JSRequest? = null
            try {
                json = JSRequest.load(text)
                var code = HttpURLConnection.HTTP_BAD_REQUEST
                var data = ""
                Request.get(json.key) { status, text ->
                    code = status
                    data = text
                }
                if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    return Response.error(json.key, "", data)
                }
                return Response.ok(json.key, data)
            } catch (error: Exception) {
                println(error.message)
                if (json == null) {
                    return Response.error("", "", ErrorMessageParse)
                }
                return Response.error(json.key, "", error.message ?: ErrorMessage)
            }
        }

        fun post(text: String): Response {
            var json: JSRequest? = null
            try {
                json = JSRequest.load(text)
                var code = HttpURLConnection.HTTP_BAD_REQUEST
                var data = ""
                Request.post(json.key, json.data) { status, text ->
                    code = status
                    data = text
                }
                if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    return Response.error(json.key, "", data)
                }
                return Response.ok(json.key, data)
            } catch (error: Exception) {
                println(error.message)
                if (json == null) {
                    return Response.error("", "", ErrorMessageParse)
                }
                return Response.error(json.key, "", error.message ?: ErrorMessage)
            }
        }

    }

}
