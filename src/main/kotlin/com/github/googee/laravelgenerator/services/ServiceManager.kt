package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.bridge.*
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.EditorManager

class ServiceManager {

    companion object {

        private val map = HashMap<String, IAction>()

        fun add(action: String, service: IAction) {
            map[action] = service
        }

        fun run(request: Request) {
            if (map.containsKey(request.action)) {
                val action = map.get(request.action)
                action?.run(request)
            }
        }

        fun register(toJS: ToJS, em: EditorManager, fm: FileManager) {
//            add("alert")
            val edit = Edit(toJS, em, fm)
            add(edit.action, edit)

            val http = HTTP(toJS)
            add("get", http)
            add("post", http)

            val read = Read(toJS, fm)
            add(read.action, read)

            val write = Write(toJS, fm)
            add(write.action, write)

            val refresh = Refresh(toJS)
            add(refresh.action, refresh)
        }

    }

}
