package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.EditorManager

class Edit(val toJS: ToJS, val em: EditorManager, val fm: FileManager) : IAction {

    val action = "edit"

    override fun run(request: Request) {
        try {
            val file = fm.getFullPath(request.key)
            FileManager.write(file, request.data)
            em.show(request, file)
            val response = Response.ok(action, request.key, "")
            toJS.send(response)
        } catch (exception: Exception) {
            val response = Response.error(action, request.key, "", exception.message)
            toJS.send(response)
        }
    }

}
