package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.http.Client
import java.net.HttpURLConnection

class HTTP : IAction {

    override val action = ActionEnum.get

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
