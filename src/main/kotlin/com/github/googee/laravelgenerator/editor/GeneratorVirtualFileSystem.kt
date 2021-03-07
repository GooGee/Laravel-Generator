package com.github.googee.laravelgenerator.editor

import com.intellij.openapi.vfs.NonPhysicalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileListener
import com.intellij.openapi.vfs.VirtualFileSystem
import java.io.IOException

class GeneratorVirtualFileSystem : VirtualFileSystem(), NonPhysicalFileSystem {

    companion object {

        private var _instance: GeneratorVirtualFileSystem? = null
        val instance: GeneratorVirtualFileSystem
            get() {
                if (_instance == null) {
                    _instance = GeneratorVirtualFileSystem()
                }
                return _instance as GeneratorVirtualFileSystem
            }
    }

    override fun getProtocol(): String {
        return "oapi"
    }

    override fun findFileByPath(path: String): VirtualFile? {
        return null
    }

    override fun refresh(asynchronous: Boolean) {
    }

    override fun refreshAndFindFileByPath(path: String): VirtualFile? {
        return null
    }

    override fun addVirtualFileListener(listener: VirtualFileListener) {
    }

    override fun removeVirtualFileListener(listener: VirtualFileListener) {
    }

    override fun deleteFile(requestor: Any?, vFile: VirtualFile) {
    }

    override fun moveFile(requestor: Any?, vFile: VirtualFile, newParent: VirtualFile) {
    }

    override fun renameFile(requestor: Any?, vFile: VirtualFile, newName: String) {
    }

    override fun createChildFile(requestor: Any?, vDir: VirtualFile, fileName: String): VirtualFile {
        throw IOException()
    }

    override fun createChildDirectory(requestor: Any?, vDir: VirtualFile, dirName: String): VirtualFile {
        throw IOException()
    }

    override fun copyFile(
        requestor: Any?,
        virtualFile: VirtualFile,
        newParent: VirtualFile,
        copyName: String
    ): VirtualFile {
        throw IOException()
    }

    override fun isReadOnly(): Boolean {
        return true
    }
}
