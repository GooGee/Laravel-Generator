package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.bridge.action.ActionEnum

class Update(val toJS: ToJS) {

    val action = ActionEnum.edit

    fun run(key: String, text: String) {
        val response = Response.ok(action.name, key, text)
        toJS.send(response)
    }

}
