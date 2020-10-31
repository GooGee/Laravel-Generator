package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.json.FileRequest
import com.google.common.io.CharStreams
import com.intellij.openapi.project.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

class FileManager(val project: Project) {
    val FileName = "code-generator.json"
    val ErrorMessage = "Error"
    val ErrorMessageParse = "Error parsing JSON data"

    fun path(file: String): String {
        return project.basePath + File.separator + file
    }

    fun load(): Response {
        try {
            val stream = FileInputStream(path(FileName))
            val reader = InputStreamReader(stream, "UTF-8")
            return Response.ok("", CharStreams.toString(reader))
        } catch (error: Exception) {
            println(error.message)
            return Response.error("", "", error.message ?: ErrorMessage)
        }
    }

    fun read(text: String): Response {
        var fr: FileRequest? = null
        try {
            fr = FileRequest(text)
            val stream = FileInputStream(path(fr.file))
            val reader = InputStreamReader(stream, "UTF-8")
            return Response.ok(fr.file, CharStreams.toString(reader))
        } catch (error: Exception) {
            println(error.message)
            if (fr == null) {
                return Response.error("", "", ErrorMessageParse)
            }
            return Response.error(fr.file, "", error.message ?: ErrorMessage)
        }
    }

    fun write(text: String): Response {
        var fr: FileRequest? = null
        try {
            fr = FileRequest(text)
            val path = Paths.get(path(fr.file))
            Files.createDirectories(path.parent)
            val writer = PrintWriter(path.toString())
            writer.println(fr.data)
            writer.close()
            return Response.ok(fr.file, "")
        } catch (error: Exception) {
            println(error.message)
            if (fr == null) {
                return Response.error("", "", ErrorMessageParse)
            }
            return Response.error(fr.file, "", error.message ?: ErrorMessage)
        }
    }

}
