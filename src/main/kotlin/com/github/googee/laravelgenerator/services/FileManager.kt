package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.json.JSRequest
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
            return Response.error("", "", error.message ?: ErrorMessage.Unknown)
        }
    }

    fun read(text: String): Response {
        var json: JSRequest? = null
        try {
            json = JSRequest.load(text)
            val stream = FileInputStream(path(json.key))
            val reader = InputStreamReader(stream, "UTF-8")
            return Response.ok(json.key, CharStreams.toString(reader))
        } catch (error: Exception) {
            println(error.message)
            if (json == null) {
                return Response.error("", "", ErrorMessage.ParseJSON)
            }
            return Response.error(json.key, "", error.message ?: ErrorMessage.Unknown)
        }
    }

    fun write(text: String): Response {
        var json: JSRequest? = null
        try {
            json = JSRequest.load(text)
            val path = Paths.get(path(json.key))
            Files.createDirectories(path.parent)
            val writer = PrintWriter(path.toString())
            writer.println(json.data)
            writer.close()
            return Response.ok(json.key, "")
        } catch (error: Exception) {
            println(error.message)
            if (json == null) {
                return Response.error("", "", ErrorMessage.ParseJSON)
            }
            return Response.error(json.key, "", error.message ?: ErrorMessage.Unknown)
        }
    }

}
