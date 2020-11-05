package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.ErrorMessage
import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.json.JSEditRequest
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.ui.content.ContentManager

class EditorManager(private val project: Project, private val manager: ContentManager, private val tb: ToBrowser) {
    val map = HashMap<String, EditorTab>()

    fun show(text: String): Response {
        var json: JSEditRequest? = null
        try {
            json = JSEditRequest.load(text)
            run(json)
            return Response.ok(json.key, "")
        } catch (error: Exception) {
            println(error.message)
            if (json == null) {
                return Response.error("", "", ErrorMessage.ParseJSON)
            }
            return Response.error(json.key, "", error.message ?: ErrorMessage.Unknown)
        }
    }

    private fun run(json: JSEditRequest) {
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

    private fun makeEditor(json: JSEditRequest) {
        val panel = EditorTab(project, json) { key, text -> tb.update(key, text) }
        map.set(json.key, panel)
        val tab = manager.factory.createContent(panel, json.key, false)
        tab.isCloseable = true
        manager.addContent(tab)
        manager.setSelectedContent(tab)
    }

}
