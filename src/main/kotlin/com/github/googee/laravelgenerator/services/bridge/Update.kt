package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.bridge.action.ActionEnum
import com.github.googee.laravelgenerator.services.file.FileManager

class Update(val fm: FileManager, val toJS: ToJS) {

    val action = ActionEnum.edit

    fun run(key: String, text: String) {
        val response = Response.ok(action.name, key, text)
        toJS.send(response)
    }

}
