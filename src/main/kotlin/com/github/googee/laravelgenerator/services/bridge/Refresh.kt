package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager

class Refresh : IAction {

    val action = "refresh"

    override fun run(request: Request): Response {
        FileManager.refresh()
        return Response.ok(action, "", "")
    }

}
