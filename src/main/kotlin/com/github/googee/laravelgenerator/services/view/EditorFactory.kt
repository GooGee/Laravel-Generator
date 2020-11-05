package com.github.googee.laravelgenerator.services.view

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFileManager
import java.nio.file.Paths

class EditorFactory {

    companion object {

        fun make(project: Project, file: String): Editor {
            val vf = VirtualFileManager.getInstance().refreshAndFindFileByNioPath(Paths.get(file))
            if (vf == null) {
                throw Exception("File $file not found!")
            }

            val document = FileDocumentManager.getInstance().getDocument(vf)
            if (document == null) {
                throw Exception("Cannot create Editor. document is null!")
            }

            return EditorFactory.getInstance().createEditor(document, project)
        }

    }

}
