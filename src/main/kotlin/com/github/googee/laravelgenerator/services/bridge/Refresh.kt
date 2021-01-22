package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager

class Refresh(val toJS: ToJS) : IAction {

    val action = "refresh"

    override fun run(request: Request) {
        FileManager.refresh()
        val response = Response.ok(action, "", "")
        toJS.send(response)
    }

}
