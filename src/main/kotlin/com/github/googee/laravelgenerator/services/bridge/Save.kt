package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.bridge.action.ActionEnum
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.file.Ignore

class Save(val toJS: ToJS, val fm: FileManager) {

    val action = ActionEnum.save
    val key = "project"

    fun run() {
        Ignore(fm).make()
        val response = Response.ok(action.name, key, "")
        toJS.send(response)
    }

}
