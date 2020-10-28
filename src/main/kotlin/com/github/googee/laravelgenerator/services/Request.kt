package com.github.googee.laravelgenerator.services

import com.google.common.io.CharStreams
import org.json.simple.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Request {

    companion object {

        fun get(uri: String, handler: (String) -> Unit) {
            run(uri, "GET", handler)
        }

        fun post(uri: String, handler: (String) -> Unit) {
            run(uri, "POST", handler)
        }

        fun run(uri: String, method: String, handler: (String) -> Unit) {
            println(uri)
            var text = ""
            try {
                val mURL = URL(uri)
                val ccc = mURL.openConnection() as HttpURLConnection
                ccc.requestMethod = method
                ccc.connectTimeout = 3111
                ccc.setRequestProperty("Accept", "application/json")
                if (ccc.responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    println(ccc.responseMessage)
                    text = makeError(ccc.responseMessage ?: "Error", ccc.responseCode.toString())
                } else {
                    text = CharStreams.toString(InputStreamReader(ccc.inputStream))
                }
            } catch (exception: Exception) {
                text = makeError(exception.message ?: "Error", "400")
            }

            handler(text)
        }

        private fun makeError(message: String, status: String): String {
            val map = hashMapOf<String, String>()
            map.set("status", status)
            map.set("message", message)
            val json = JSONObject(map)
            return json.toJSONString()
        }
    }

}
