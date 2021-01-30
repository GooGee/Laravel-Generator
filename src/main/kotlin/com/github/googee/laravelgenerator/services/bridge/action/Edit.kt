package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.Constant.Companion.CGFolder
import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.http.Client
import com.github.googee.laravelgenerator.services.http.Site
import com.github.googee.laravelgenerator.services.view.EditorManager

class Edit(val em: EditorManager, val fm: FileManager) : IAction {

    override val action = "edit"

    val FileName = "index.d.ts"
    val FilePath = "$CGFolder/script/$FileName"
    val URI = Site.getURI() + "/$FileName"

    override fun run(request: Request): Response {
        return try {
            copyDTS()
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

    private fun copyDTS() {
        val file = fm.getFullPath(FilePath)
        if (FileManager.isFile(file)) {
            return
        }
        FileManager.makeFolder(file)
        Client.copy(URI, file)
    }

}
