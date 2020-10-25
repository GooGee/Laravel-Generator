package com.github.googee.laravelgenerator.services

import com.google.common.io.CharStreams
import com.intellij.ide.impl.ProjectUtil
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

class FileManager {
    companion object {
        const val FileName = "code-generator.json"

        fun path(file: String): String {
            return ProjectUtil.getBaseDir() + File.separator + file
        }

        fun make(text: String) {
            try {
                val index = text.indexOf('*')
                val file = text.substring(0, index)
                val path = Paths.get(path(file))
                Files.createDirectories(path.parent)
                val content = text.substring(index + 1)
                val writer = PrintWriter(path.toString())
                writer.println(content)
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
}