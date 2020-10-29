package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.json.MakeFile
import com.google.common.io.CharStreams
import com.intellij.openapi.project.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths
import kotlinx.serialization.json.Json

class FileManager(val project: Project) {
    val FileName = "code-generator.json"

    fun path(file: String): String {
        return project.basePath + File.separator + file
    }

    fun make(text: String) {
        try {
            val data = Json.decodeFromString(MakeFile.serializer(), text)
            val path = Paths.get(path(data.file))
            Files.createDirectories(path.parent)
            val writer = PrintWriter(path.toString())
            writer.println(data.content)
            writer.close()
        } catch (error: Exception) {
            println(error.message)
        }
    }

    fun load(): String {
        try {
            val stream = FileInputStream(path(FileName))
            val reader = InputStreamReader(stream)
            return CharStreams.toString(reader)
        } catch (error: Exception) {
            println(error.message)
        }

        return ""
    }

    fun save(text: String) {
//            println(text)
        try {
            val writer = PrintWriter(path(FileName))
            writer.println(text)
            writer.close()
        } catch (error: Exception) {
            println(error.message)
        }
    }
}
