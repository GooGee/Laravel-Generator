package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.json.JSRequest
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.ui.content.ContentManager

class EditorManager(private val project: Project, private val manager: ContentManager, private val tb: ToBrowser, private val fm: FileManager) {
    val map = HashMap<String, EditorTab>()

    fun show(json: JSRequest) {
        ApplicationManager.getApplication().invokeLater(Runnable() {
            run() {
                val tab = manager.findContent(json.key)
                if (tab == null) {
                    makeEditor(json)
                } else {
                    val panel = map.get(json.key)
                    panel?.update(json)
                    manager.setSelectedContent(tab)
                }
            }
        })
    }

    private fun makeEditor(json: JSRequest) {
        val panel = EditorTab(project, json, fm) { key, text -> tb.update(key, text) }
        map.set(json.key, panel)
        val tab = manager.factory.createContent(panel, json.key, false)
        tab.isCloseable = true
        manager.addContent(tab)
        manager.setSelectedContent(tab)
    }

}
