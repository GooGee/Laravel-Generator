package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import java.awt.Desktop
import java.net.URI

class Open : IAction {

    override val action = "open"

    override fun run(request: Request): Response {
        return try {
            Desktop.getDesktop().browse(URI(request.key))
            Response.ok(action, request.key, "")
        } catch (exception: Exception) {
            Response.error(action, request.key, "", exception.message)
        }
    }

}
