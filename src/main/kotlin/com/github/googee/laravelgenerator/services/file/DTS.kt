package com.github.googee.laravelgenerator.services.file

import com.github.googee.laravelgenerator.services.http.Client
import com.github.googee.laravelgenerator.services.http.Site
import com.intellij.openapi.project.Project

class DTS {

    companion object {

        const val FileName = "index.d.ts"
        val URI = Site.getURI() + "/$FileName"

        fun make(project: Project) {
            val file = FileManager.getGeneratorFile(FileName, project)
            if (FileManager.isFile(file)) {
                return
            }
            FileManager.makeFolder(file)
            Client.copy(URI, file)
        }
    }
}
