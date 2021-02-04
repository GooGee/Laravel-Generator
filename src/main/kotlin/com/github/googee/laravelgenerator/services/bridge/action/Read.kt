package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager

class Read(val fm: FileManager) : IAction {

    override val action = ActionEnum.read

    override fun run(request: Request): Response {
        return try {
            val file = fm.getFullPath(request.key)
            val content = FileManager.read(file)
            Response.ok(action.name, request.key, content)
        } catch (exception: Exception) {
            Response.error(action.name, request.key, "", exception.message)
        }
    }

}
