package com.github.googee.laravelgenerator.services

import com.google.common.io.CharStreams
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class Request {

    companion object {
        const val ErrorMessage = "Error"

        fun get(uri: String, handler: (Int, String) -> Unit) {
            run(uri, "GET", "", handler)
        }

        fun post(uri: String, json: String, handler: (Int, String) -> Unit) {
            run(uri, "POST", json, handler)
        }

        fun run(uri: String, method: String, json: String, handler: (Int, String) -> Unit) {
            println(uri)
            var status = HttpURLConnection.HTTP_BAD_REQUEST
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

                status = ccc.responseCode
                if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    text = ccc.responseMessage ?: ErrorMessage
                } else {
                    val reader = InputStreamReader(ccc.inputStream, "UTF-8")
                    text = CharStreams.toString(reader)
                }
            } catch (exception: Exception) {
                text = exception.message ?: ErrorMessage
            }

            handler(status, text)
        }

    }

}
