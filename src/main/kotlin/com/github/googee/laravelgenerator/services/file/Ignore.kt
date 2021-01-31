package com.github.googee.laravelgenerator.services.file

class Ignore(val fm: FileManager) {

    val FileName = ".gitignore"

    fun make() {
        val file = fm.getCGFile(FileName)
        if (FileManager.isFile(file)) {
            return
        }
        FileManager.makeFolder(file)
        FileManager.write(file, "*")
    }

}
