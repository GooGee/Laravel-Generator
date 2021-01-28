package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.EditorManager

class Edit(val em: EditorManager, val fm: FileManager) : IAction {

    override val action = "edit"

    override fun run(request: Request): Response {
        return try {
            val file = fm.getFullPath(request.key)
            if (FileManager.isFile(file) == false) {
                FileManager.write(file, request.data)
            }
            em.show(request, file)
            Response.ok(action, request.key, request.data)
        } catch (exception: Exception) {
            Response.error(action, request.key, "", exception.message)
        }
    }

}
