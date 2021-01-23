package com.github.googee.laravelgenerator.services.bridge

class Update {

    val action = "update"

    fun run(key: String, text: String): Response {
        return Response.ok(action, key, text)
    }

}
