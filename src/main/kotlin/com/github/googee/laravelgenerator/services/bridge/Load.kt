package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.bridge.action.ActionEnum
import com.github.googee.laravelgenerator.services.file.FileManager

class Load(val fm: FileManager, val toJS: ToJS) {

    val action = ActionEnum.load
    val key = "project"

    val FileName = "data.json"

    fun run() {
        val file = fm.getCGFile(FileName)
        try {
            move(file)
            val text = FileManager.read(file)
            val response = Response.ok(action.name, key, text)
            toJS.send(response)
        } catch (exception: Exception) {
            val response = Response.error(action.name, key, "", exception.message)
            toJS.send(response)
        }
    }

    /**
     * move code-generator.json
     * to code-generator/data.json
     */
    private fun move(destination: String) {
        if (FileManager.isFile(destination)) {
            return
        }

        val file = fm.getFullPath("code-generator.json")
        if (FileManager.isFile(file)) {
            FileManager.move(file, destination)
        }
    }

}
