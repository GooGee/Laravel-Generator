package com.github.googee.laravelgenerator.services.bridge.action

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Response

interface IAction {

    val action: String

    fun run(request: Request): Response

}
