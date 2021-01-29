package com.github.googee.laravelgenerator.services.http

import com.github.googee.laravelgenerator.services.Constant
import com.github.googee.laravelgenerator.services.Version

class Site {
    companion object {
        const val WebKey = "WebURI"

        fun getURI(): String {
            val uri = System.getenv(WebKey)
            if (uri.isNullOrEmpty()) {
                return Constant.URI + "/dist" + Version.get()
            }
            return uri
        }

    }
}
