package com.github.googee.laravelgenerator.services.bridge

class Update(val toJS: ToJS) {

    val action = "update"

    fun run(key: String, text: String) {
        val response = Response.ok(action, key, text)
        toJS.send(response)
    }

}
