package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.http.Client
import java.net.HttpURLConnection

class HTTP : IAction {

    override fun run(request: Request): Response {
        var code = HttpURLConnection.HTTP_BAD_REQUEST
        var data = ""
        Client.run(request.key, request.action, request.data) { status, text ->
            code = status
            data = text
        }

        if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
            return Response.error(request.action, request.key, "", data)
        }
        return Response.ok(request.action, request.key, data)
    }

}