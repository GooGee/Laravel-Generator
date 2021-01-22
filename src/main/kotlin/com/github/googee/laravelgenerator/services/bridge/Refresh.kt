package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager

class Refresh(val toJS: ToJS) {

    val action = "refresh"

    fun run() {
        FileManager.refresh()
        val response = Response.ok(action, "", "")
        toJS.send(response)
    }

}
