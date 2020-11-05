package com.github.googee.laravelgenerator.services.loader

import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.file.FileManager
import java.io.File

class Loader(val manager: FileManager) {

    fun load(): Response {
        val path = manager.getGeneratorFile()
        val file = File(path)
        if (file.isDirectory) {
            throw Exception("Please delete folder $path")
        }

        if (file.exists()) {
            return Loader20.load(file)
        }

        return LoaderV13.load(manager.getV13File())
    }

}
