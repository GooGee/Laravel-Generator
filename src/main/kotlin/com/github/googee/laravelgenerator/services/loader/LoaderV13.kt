package com.github.googee.laravelgenerator.services.loader

import com.github.googee.laravelgenerator.services.ErrorMessage
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager

class LoaderV13 {

    companion object {

        fun load(file: String): Response {
            try {
                return Response.ok("", FileManager.read(file))
            } catch (error: Exception) {
                println(error.message)
                return Response.error("", "", error.message ?: ErrorMessage.Unknown)
            }
        }

    }

}
