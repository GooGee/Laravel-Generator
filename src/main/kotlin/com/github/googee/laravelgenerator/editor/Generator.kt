package com.github.googee.laravelgenerator.editor

import com.github.googee.laravelgenerator.services.Constant
import com.github.googee.laravelgenerator.services.MyProjectService
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorLocation
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.util.UserDataHolderBase
import java.beans.PropertyChangeListener
import javax.swing.JComponent

class Generator : UserDataHolderBase(), FileEditor {

    companion object {

        const val EditorType = Constant.Title
    }

    override fun dispose() {
    }

    override fun getComponent(): JComponent {
        return MyProjectService.instance.view
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return MyProjectService.instance.view
    }

    override fun getName(): String {
        return Constant.Title
    }

    override fun setState(state: FileEditorState) {
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun isValid(): Boolean {
        return true
    }

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {
    }

    override fun removePropertyChangeListener(listener: PropertyChangeListener) {
    }

    override fun getCurrentLocation(): FileEditorLocation? {
        return null
    }
}
