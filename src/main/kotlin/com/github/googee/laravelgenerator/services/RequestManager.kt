package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.json.QueryRequest
import java.net.HttpURLConnection

class RequestManager {

    companion object {
        const val ErrorMessage = "Error"
        const val ErrorMessageParse = "Error parsing JSON data"

        fun get(text: String): Response {
            var qr: QueryRequest? = null
            try {
                qr = QueryRequest(text)
                var code = HttpURLConnection.HTTP_BAD_REQUEST
                var data = ""
                Request.get(qr.route) { status, text ->
                    code = status
                    data = text
                }
                if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    return Response.error(qr.route, "", data)
                }
                return Response.ok(qr.route, data)
            } catch (error: Exception) {
                println(error.message)
                if (qr == null) {
                    return Response.error("", "", ErrorMessageParse)
                }
                return Response.error(qr.route, "", error.message ?: ErrorMessage)
            }
        }

        fun post(text: String): Response {
            var qr: QueryRequest? = null
            try {
                qr = QueryRequest(text)
                var code = HttpURLConnection.HTTP_BAD_REQUEST
                var data = ""
                Request.post(qr.route, qr.data) { status, text ->
                    code = status
                    data = text
                }
                if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    return Response.error(qr.route, "", data)
                }
                return Response.ok(qr.route, data)
            } catch (error: Exception) {
                println(error.message)
                if (qr == null) {
                    return Response.error("", "", ErrorMessageParse)
                }
                return Response.error(qr.route, "", error.message ?: ErrorMessage)
            }
        }

    }

}
