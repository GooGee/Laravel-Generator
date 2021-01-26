package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager

class Write(val fm: FileManager) : IAction {

    override val action = "write"

    override fun run(request: Request): Response {
        return try {
            val file = fm.getFullPath(request.key)
            FileManager.write(file, request.data)
            Response.ok(action, request.key, "")
        } catch (exception: Exception) {
            Response.error(action, request.key, "", exception.message)
        }
    }

}
