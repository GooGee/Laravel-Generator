package com.github.googee.laravelgenerator.editor

import com.github.googee.laravelgenerator.services.Constant
import com.intellij.openapi.fileTypes.ex.FakeFileType
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class GeneratorFileType : FakeFileType() {
    companion object {
        val instance = GeneratorFileType()
    }

    override fun getName(): String {
        return Constant.Title
    }

    override fun getDescription(): String {
        return Constant.Title
    }

    override fun getIcon(): Icon? {
        return IconLoader.getIcon("/image/icon.svg")
    }

    override fun isMyFileType(file: VirtualFile): Boolean {
        return file is GeneratorVirtualFile
    }
}
