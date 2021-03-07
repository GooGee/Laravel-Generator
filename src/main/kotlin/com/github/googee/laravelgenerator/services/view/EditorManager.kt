package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Update
import com.github.googee.laravelgenerator.services.file.FileManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class EditorManager {

    companion object {

        fun find(manager: FileEditorManager, vf: VirtualFile): FileEditor? {
            manager.allEditors.forEach {
                if (it.file == vf) {
                    return it
                }
            }
            return null
        }

        fun make(manager: FileEditorManager, vf: VirtualFile, request: Request, update: Update) {
            val editorxx = manager.openFile(vf, true)
            val editor = editorxx[0] as TextEditor
            val view = EditorView() { e ->
                FileDocumentManager.getInstance().saveDocument(editor.editor.document)
                update.run(request.key, editor.editor.document.text)
            }
            manager.addTopComponent(editor, view)
        }

        fun show(request: Request, project: Project, update: Update) {
            ApplicationManager.getApplication().invokeLater(Runnable() {
                run() {
                    val manager = FileEditorManager.getInstance(project)
                    val path = FileManager.getFullPath(request.key, project)
                    val vf = FileManager.getVF(path)
                    if (vf == null) {
                        return@Runnable
                    }
                    val found = find(manager, vf)
                    if (found == null) {
                        make(manager, vf, request, update)
                    } else {
                        manager.openFile(vf, true)
                    }
                }
            })
        }
    }
}
