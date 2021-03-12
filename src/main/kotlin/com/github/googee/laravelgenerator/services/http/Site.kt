package com.github.googee.laravelgenerator.services.http

import com.github.googee.laravelgenerator.services.Version

class Site {
    companion object {

        const val Key = "URI"

        const val URI = "https://googee.github.io/Code-Generator-Page"

        fun getURI(): String {
            val uri = System.getenv(Key)
            if (uri.isNullOrEmpty()) {
                return URI + "/dist" + Version.get()
            }
            return uri
        }
    }
}
