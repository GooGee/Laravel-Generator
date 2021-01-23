package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.EditorManager

class Edit(val em: EditorManager, val fm: FileManager) : IAction {

    val action = "edit"

    override fun run(request: Request): Response {
        return try {
            val file = fm.getFullPath(request.key)
            FileManager.write(file, request.data)
            em.show(request, file)
            Response.ok(action, request.key, request.data)
        } catch (exception: Exception) {
            Response.error(action, request.key, "", exception.message)
        }
    }

}
