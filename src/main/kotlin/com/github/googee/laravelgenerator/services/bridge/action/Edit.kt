package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.DTS
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.EditorManager

class Edit(val em: EditorManager, val fm: FileManager) : IAction {

    override val action = ActionEnum.edit

    override fun run(request: Request): Response {
        return try {
            DTS(fm).make()
            val file = fm.getFullPath(request.key)
            if (FileManager.isFile(file) == false) {
                FileManager.write(file, request.data)
            }
            em.show(request, file)
            Response.ok(action.name, request.key, request.data)
        } catch (exception: Exception) {
            Response.error(action.name, request.key, "", exception.message)
        }
    }

}
