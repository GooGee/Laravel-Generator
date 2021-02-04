package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager

class Move(val fm: FileManager) : IAction {

    override val action = ActionEnum.move

    override fun run(request: Request): Response {
        return try {
            val old = fm.getFullPath(request.key)
            val new = fm.getFullPath(request.data)
            FileManager.move(old, new)
            Response.ok(action.name, request.key, request.data)
        } catch (exception: Exception) {
            Response.error(action.name, request.key, "", exception.message)
        }
    }

}
