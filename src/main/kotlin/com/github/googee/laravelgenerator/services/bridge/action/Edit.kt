package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.bridge.Update
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.EditorManager
import com.intellij.openapi.project.Project

class Edit(val project: Project, val update: Update) : IAction {

    override val action = ActionEnum.edit

    override fun run(request: Request): Response {
        return try {
            val file = FileManager.getFullPath(request.key, project)
            if (FileManager.isFile(file) == false) {
                FileManager.write(file, request.data)
            }
            EditorManager.show(request, project, update)
            Response.ok(action.name, request.key, request.data)
        } catch (exception: Exception) {
            Response.error(action.name, request.key, "", exception.message)
        }
    }
}
