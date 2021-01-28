package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Update
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.ui.content.ContentManager

class EditorManager(private val project: Project, private val manager: ContentManager, private val update: Update) {
    val map = HashMap<String, EditorTab>()

    fun has(file: String): Boolean {
        if (manager.findContent(file) == null) {
            return false
        }
        return true
    }

    fun show(json: Request, file: String) {
        ApplicationManager.getApplication().invokeLater(Runnable() {
            run() {
                val tab = manager.findContent(json.key)
                if (tab == null) {
                    makeEditor(json, file)
                } else {
                    manager.setSelectedContent(tab)
                }
            }
        })
    }

    private fun makeEditor(json: Request, file: String) {
        val editor = EditorFactory.make(project, file)
        val panel = EditorTab(editor, json, update)
        map.set(json.key, panel)
        val tab = manager.factory.createContent(panel, json.key, false)
        tab.isCloseable = true
        panel.closeHandler = { -> manager.removeContent(tab, true) }
        manager.addContent(tab)
        manager.setSelectedContent(tab)
    }

}
