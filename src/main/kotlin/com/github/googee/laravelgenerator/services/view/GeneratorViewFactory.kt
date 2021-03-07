package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.editor.GeneratorVirtualFile
import com.github.googee.laravelgenerator.editor.GeneratorVirtualFileSystem
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.ui.jcef.JBCefBrowser

class GeneratorViewFactory {

    companion object {

        private var vf: GeneratorVirtualFile? = null

        fun find(manager: FileEditorManager): FileEditor? {
            if (vf == null) {
                return null
            }
            manager.allEditors.forEach {
                if (it.file == vf) {
                    return it
                }
            }
            return null
        }

        fun make(browser: JBCefBrowser): GeneratorView {
            return GeneratorView(browser)
        }

        fun show(project: Project) {
            val manager = FileEditorManager.getInstance(project)
            val found = find(manager)
            if (found == null) {
                if (vf == null) {
                    vf = GeneratorVirtualFile(GeneratorVirtualFileSystem.instance)
                }
                manager.openFile(vf!!, true)
            } else {
                manager.openFile(found.file!!, true)
            }
        }
    }
}
