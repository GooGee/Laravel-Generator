package com.github.googee.laravelgenerator.services.request

import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.http.Client
import com.github.googee.laravelgenerator.services.json.JSRequest
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery
import java.net.HttpURLConnection

open class HTTPRequest : Request() {

    fun register(browser: JBCefBrowser, tb: ToBrowser, isPost: Boolean = false): String {
        val query = JBCefJSQuery.create(browser)
        var name = "get"
        if (isPost) {
            name = "post"
        }
        query.addHandler { text ->
            val json = JSRequest.load(text)
            tb.run(getHandlerName(name), run(json, isPost).toJSON())
            null
        }
        return makeFunction(name, query)
    }

    private fun run(json: JSRequest, isPost: Boolean = false): Response {
        var code = HttpURLConnection.HTTP_BAD_REQUEST
        var data = ""
        if (isPost) {
            Client.post(json.key, json.data) { status, text ->
                code = status
                data = text
            }
        } else {
            Client.get(json.key, json.data) { status, text ->
                code = status
                data = text
            }
        }

        if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
            return Response.error(json.key, "", data)
        }
        return Response.ok(json.key, data)
    }

}
