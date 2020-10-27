package com.github.googee.laravelgenerator.services

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Request {

    companion object {

        fun get(uri: String, handler: (StringBuffer) -> Unit) {
            val mURL = URL(uri)
            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "GET"
                getResponse(inputStream, handler)
            }
        }

        fun getResponse(inputStream: java.io.InputStream, handler: (StringBuffer) -> Unit) {
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()

                handler(response)
            }
        }
    }

}
