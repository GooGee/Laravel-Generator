package com.github.googee.laravelgenerator.services.http

import java.util.*

class Site {
    companion object {
        const val FileName = "/data.properties"
        const val WebKey = "WebURI"

        fun getURI(): String {
            val uri = System.getenv(WebKey)
            if (uri.isNullOrEmpty()) {
                val properties = Properties()
                properties.load(object {}.javaClass.getResourceAsStream(FileName))
                return properties.get(WebKey) as String
            }
            return uri
        }

    }
}
