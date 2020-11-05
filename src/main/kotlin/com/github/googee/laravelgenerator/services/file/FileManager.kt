package com.github.googee.laravelgenerator.services.file

import com.google.common.io.CharStreams
import com.intellij.openapi.project.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

class FileManager(val project: Project) {

    companion object {

        const val GeneratorFile = "data.json"
        const val GeneratorFolder = "code-generator"
        const val ScriptFolder = "script"
        const val TemplateFolder = "template"
        const val V13File = "code-generator.json"

        fun read(file: String): String {
            val stream = FileInputStream(file)
            return read(stream)
        }

        fun read(file: File): String {
            val stream = FileInputStream(file)
            return read(stream)
        }

        fun read(stream: FileInputStream): String {
            val reader = InputStreamReader(stream, "UTF-8")
            return CharStreams.toString(reader)
        }

        fun write(file: String, text: String) {
            val path = Paths.get(file)
            Files.createDirectories(path.parent)
            val writer = PrintWriter(path.toString())
            writer.println(text)
            writer.close()
        }

    }

    fun getGeneratorFile(): String {
        return getFile(GeneratorFolder + File.separator + GeneratorFile)
    }

    fun getFile(file: String): String {
        return project.basePath + File.separator + file
    }

    fun getScript(file: String): String {
        return getFile(GeneratorFolder + File.separator + ScriptFolder + File.separator + file)
    }

    fun getTemplate(file: String): String {
        return getFile(GeneratorFolder + File.separator + TemplateFolder + File.separator + file)
    }

    fun getV13File(): String {
        return getFile(V13File)
    }

}
