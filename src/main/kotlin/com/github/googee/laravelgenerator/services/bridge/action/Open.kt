package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response
import java.awt.Desktop
import java.net.URI

class Open : IAction {

    override val action = ActionEnum.open

    override fun run(request: Request): Response {
        return try {
            Desktop.getDesktop().browse(URI(request.key))
            Response.ok(action.name, request.key, "")
        } catch (exception: Exception) {
            Response.error(action.name, request.key, "", exception.message)
        }
    }

}
