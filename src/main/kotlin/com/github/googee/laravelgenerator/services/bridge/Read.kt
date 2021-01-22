package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager

class Read(val toJS: ToJS, val fm: FileManager) {

    val action = "read"

    fun run(request: Request) {
        try {
            val file = fm.getFullPath(request.key)
            val content = FileManager.read(file)
            val response = Response.ok(action, request.key, content)
            toJS.send(response)
        } catch (exception: Exception) {
            val response = Response.error(action, request.key, "", exception.message)
            toJS.send(response)
        }
    }

}
