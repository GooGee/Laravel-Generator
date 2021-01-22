package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.ErrorMessage
import com.github.googee.laravelgenerator.services.file.FileManager

class Load(val fm: FileManager, val toJS: ToJS) {

    val action = "load"
    val key = "project"

    fun run() {
        val file = fm.getFullPath(FileManager.GeneratorFile)
        try {
            val text = FileManager.read(file)
            val response = Response.ok(action, key, text)
            toJS.send(response)
        } catch (exception: Exception) {
            val message = exception.message ?: ErrorMessage.Unknown
            val response = Response.error(action, key, "", message)
            toJS.send(response)
        }
    }

}
