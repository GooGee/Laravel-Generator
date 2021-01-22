package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager

class Write(val toJS: ToJS, val fm: FileManager) : IAction {

    val action = "write"

    override fun run(request: Request) {
        try {
            val file = fm.getFullPath(request.key)
            FileManager.write(file, request.data)
            val response = Response.ok(action, request.key, "")
            toJS.send(response)
        } catch (exception: Exception) {
            val response = Response.error(action, request.key, "", exception.message)
            toJS.send(response)
        }
    }

}
