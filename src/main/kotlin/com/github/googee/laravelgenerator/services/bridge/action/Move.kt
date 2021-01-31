package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager
import java.io.File

class Move(val fm: FileManager) : IAction {

    override val action = "move"

    override fun run(request: Request): Response {
        return try {
            val old = File(fm.getFullPath(request.key))
            val path = fm.getFullPath(request.data)
            FileManager.makeFolder(path)
            val file = File(path)
            old.renameTo(file)
            Response.ok(action, request.key, request.data)
        } catch (exception: Exception) {
            Response.error(action, request.key, "", exception.message)
        }
    }

}
