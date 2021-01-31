package com.github.googee.laravelgenerator.services.file

import com.github.googee.laravelgenerator.services.http.Client
import com.github.googee.laravelgenerator.services.http.Site

class DTS(val fm: FileManager) {

    val FileName = "index.d.ts"
    val FilePath = "script/$FileName"
    val URI = Site.getURI() + "/$FileName"

    fun make() {
        val file = fm.getCGFile(FilePath)
        if (FileManager.isFile(file)) {
            return
        }
        FileManager.makeFolder(file)
        Client.copy(URI, file)
    }

}
