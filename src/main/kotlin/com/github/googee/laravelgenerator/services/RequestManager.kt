package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.bridge.action.*
import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.bridge.Update
import com.github.googee.laravelgenerator.services.file.FileManager
import com.intellij.openapi.project.Project

class RequestManager {

    companion object {

        private val map = HashMap<String, IAction>()

        fun add(action: String, service: IAction) {
            map[action] = service
        }

        fun handle(request: Request): Response {
            if (map.containsKey(request.action)) {
                val action = map.get(request.action)
                if (action != null) {
                    return action.run(request)
                }
            }
            return Response.error(request.action, request.key, "", ErrorMessage.ActionNotFound)
        }

        fun register(fm: FileManager, update: Update, project: Project) {
            val edit = Edit(project, update)
            add(edit.action.name, edit)

            val http = HTTP()
            add(ActionEnum.get.name, http)
            add(ActionEnum.post.name, http)

            val move = Move(fm)
            add(move.action.name, move)

            val open = Open()
            add(open.action.name, open)

            val read = Read(fm)
            add(read.action.name, read)

            val write = Write(fm)
            add(write.action.name, write)

            val refresh = Refresh()
            add(refresh.action.name, refresh)
        }
    }
}
