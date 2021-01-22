package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.http.Client
import java.net.HttpURLConnection

class HTTP(val toJS: ToJS) {

    fun run(request: Request) {
        val response = post(request)
        toJS.send(response)
    }

    private fun post(request: Request): Response {
        var code = HttpURLConnection.HTTP_BAD_REQUEST
        var data = ""
        if (request.action == "post") {
            Client.post(request.key, request.data) { status, text ->
                code = status
                data = text
            }
        } else {
            Client.get(request.key, request.data) { status, text ->
                code = status
                data = text
            }
        }

        if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
            return Response.error(request.action, request.key, "", data)
        }
        return Response.ok(request.action, request.key, data)
    }

}
