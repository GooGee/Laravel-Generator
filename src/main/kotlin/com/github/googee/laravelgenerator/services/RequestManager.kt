package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.bridge.action.*
import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.EditorManager

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

        fun register(em: EditorManager, fm: FileManager) {
            val edit = Edit(em, fm)
            add(edit.action, edit)

            val http = HTTP()
            add("get", http)
            add("post", http)

            val read = Read(fm)
            add(read.action, read)

            val write = Write(fm)
            add(write.action, write)

            val refresh = Refresh()
            add(refresh.action, refresh)
        }

    }

}
