package com.github.googee.laravelgenerator.services.view

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.fileTypes.FileTypes
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.StdFileTypes
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory

class EditorFactory {

    companion object {

        fun getFileType(type: String): LanguageFileType {
            if (type == "js") {
                return StdFileTypes.JS
            }
            return FileTypes.PLAIN_TEXT
        }

        fun make(project: Project, type: String, code: String): Editor {
            val fileType = getFileType(type)
            val file = PsiFileFactory.getInstance(project).createFileFromText(fileType.language, code)
            val document = file.viewProvider.document
            if (document == null) {
                throw Exception("Cannot create Editor. document is null!")
            }
            return EditorFactory.getInstance().createEditor(document, project, fileType, false)
        }

    }

}
