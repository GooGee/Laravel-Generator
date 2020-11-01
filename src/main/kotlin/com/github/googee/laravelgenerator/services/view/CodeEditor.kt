package com.github.googee.laravelgenerator.services.view

import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.ui.EditorTextField

class CodeEditor(code: String) : EditorTextField(code) {

    override fun createEditor(): EditorEx {
        val editor = super.createEditor()
        editor.setOneLineMode(false)
        editor.setHorizontalScrollbarVisible(true)
        editor.setVerticalScrollbarVisible(true)
        return editor
    }

}
