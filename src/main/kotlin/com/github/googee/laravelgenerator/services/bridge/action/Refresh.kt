package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager

class Refresh : IAction {

    val action = "refresh"

    override fun run(request: Request): Response {
        FileManager.refresh()
        return Response.ok(action, "", "")
    }

}
