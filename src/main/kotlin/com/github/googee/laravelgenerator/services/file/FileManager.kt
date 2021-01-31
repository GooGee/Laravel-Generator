package com.github.googee.laravelgenerator.services.file

import com.github.googee.laravelgenerator.services.Constant
import com.google.common.io.CharStreams
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

class FileManager(val project: Project) {

    companion object {

        fun isFile(file: String): Boolean {
            return File(file).isFile
        }

        fun makeFolder(file: String) {
            val path = Paths.get(file)
            Files.createDirectories(path.parent)
        }

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
            writer.print(text)
            writer.close()
        }

        fun refresh() {
            println("-- refresh --")
            LocalFileSystem.getInstance().refresh(true)
        }

    }

    fun getFullPath(file: String): String {
        return project.basePath + File.separator + file
    }

    fun getCGFile(file: String): String {
        return getFullPath(Constant.CGFolder + File.separator + file)
    }
}
