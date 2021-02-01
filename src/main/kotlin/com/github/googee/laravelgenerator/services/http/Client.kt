package com.github.googee.laravelgenerator.services.http

import com.github.googee.laravelgenerator.services.ErrorMessage
import com.google.common.io.CharStreams
import com.jetbrains.rd.util.printlnError
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.channels.Channels
import java.nio.charset.Charset

class Client {

    companion object {

        fun run(uri: String, method: String, data: String, handler: (Int, String) -> Unit) {
            println("$method $uri")
            var status = HttpURLConnection.HTTP_BAD_REQUEST
            var text = ""
            try {
                val mURL = URL(uri)
                val ccc = mURL.openConnection() as HttpURLConnection
                ccc.requestMethod = method.toUpperCase()
                ccc.connectTimeout = 3111
                ccc.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                ccc.setRequestProperty("Accept", "application/json")

                if (data.isNotEmpty()) {
                    if (!ccc.doOutput) {
                        ccc.doOutput = true
                    }
                    val os = ccc.outputStream
                    os.write(data.toByteArray(Charset.forName("UTF-8")))
                    os.close()
                }

                status = ccc.responseCode
                if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                    text = ccc.responseMessage
                } else {
                    val reader = InputStreamReader(ccc.inputStream, "UTF-8")
                    text = CharStreams.toString(reader)
                }
            } catch (exception: Exception) {
                text = ErrorMessage.check(exception.message)
                printlnError(text)
            }

            handler(status, text)
        }

        fun copy(uri: String, file: String) {
            val channel = Channels.newChannel(URL(uri).openStream())
            FileOutputStream(file).channel.transferFrom(channel, 0, Long.MAX_VALUE)
        }

    }

}
