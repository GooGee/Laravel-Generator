package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.bridge.Request
import com.github.googee.laravelgenerator.services.bridge.Update
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.ui.content.ContentManager
import com.intellij.ui.content.ContentManagerEvent
import com.intellij.ui.content.ContentManagerListener

class EditorManager(private val project: Project, private val manager: ContentManager, private val update: Update) {
    val map = HashMap<String, EditorTab>()

    fun show(json: Request, file: String) {
        ApplicationManager.getApplication().invokeLater(Runnable() {
            run() {
                val tab = manager.findContent(json.key)
                if (tab == null) {
                    makeEditor(json, file)
                } else {
                    val panel = map.get(json.key)
                    panel?.update(json)
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
        manager.addContentManagerListener(object : ContentManagerListener {
            override fun contentRemoved(event: ContentManagerEvent) {
                FileDocumentManager.getInstance().saveDocument(editor.document)
                update.run(json.key, editor.document.text)
            }
        })
    }

}
