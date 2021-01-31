package com.github.googee.laravelgenerator.services.bridge

class Save(val toJS: ToJS) {

    val action = "save"
    val key = "project"

    fun run() {
        val response = Response.ok(action, key, "")
        toJS.send(response)
    }

}
