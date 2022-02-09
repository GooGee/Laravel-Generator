package com.github.googee.laravelgenerator.services.http

import com.github.googee.laravelgenerator.services.Version
import com.github.googee.laravelgenerator.services.file.FileManager

class Site {
    companion object {

        const val Key = "URI"

        const val URI = "https://googee.github.io/Code-Generator-Page"

        fun getURI(fm: FileManager): String {
            // build plugin
            val uriFile = fm.getCGFile(Key)
            if (FileManager.isFile(uriFile)) {
                return FileManager.read(uriFile)
            }

            // runIde
            val uri = System.getenv(Key)
            if (uri.isNullOrEmpty()) {
                return URI + "/dist" + Version.get()
            }
            return uri
        }
    }
}
