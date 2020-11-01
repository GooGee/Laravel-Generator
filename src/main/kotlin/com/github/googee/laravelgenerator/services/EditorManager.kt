package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.json.JSEditRequest
import com.github.googee.laravelgenerator.services.view.EditorTab
import com.intellij.openapi.application.ApplicationManager
import com.intellij.ui.content.ContentManager

class EditorManager(private val manager: ContentManager, private val tb: ToBrowser) {

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
                    manager.setSelectedContent(tab)
                }
            }
        })
    }

    private fun makeEditor(json: JSEditRequest) {
        val panel = EditorTab(json) { key, text -> tb.update(key, text) }
        val tab = manager.factory.createContent(panel, json.key, false)
        tab.isCloseable = true
        manager.addContent(tab)
        manager.setSelectedContent(tab)
    }

}
