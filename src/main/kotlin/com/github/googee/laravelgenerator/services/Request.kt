package com.github.googee.laravelgenerator.services

import com.google.common.io.CharStreams
import org.json.simple.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class Request {

    companion object {

        fun get(uri: String, handler: (String) -> Unit) {
            run(uri, "GET", "", handler)
        }

        fun post(uri: String, json: String, handler: (String) -> Unit) {
            run(uri, "POST", json, handler)
        }

        fun run(uri: String, method: String, json: String, handler: (String) -> Unit) {
            println(uri)
            var text = ""
            try {
                val mURL = URL(uri)
                val ccc = mURL.openConnection() as HttpURLConnection
                ccc.requestMethod = method
                ccc.connectTimeout = 3111
                ccc.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                ccc.setRequestProperty("Accept", "application/json")

                if (json.isNotEmpty()) {
                    if (!ccc.doOutput) {
                        ccc.doOutput = true
                    }
                    val os = ccc.outputStream
                    os.write(json.toByteArray(Charset.forName("UTF-8")))
                    os.close()
                }

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
