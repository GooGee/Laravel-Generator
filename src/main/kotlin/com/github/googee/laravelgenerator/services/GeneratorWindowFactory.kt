package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser

class GeneratorWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        if (!JBCefApp.isSupported()) {
            return
        }

        println("create browser")
        val uri = "http://localhost:8080/"
        val browser = JBCefBrowser(uri)
        val code = QueryManager.register(browser)
        val handler = JCEFLoadHandler(browser, uri, code)
        browser.jbCefClient.addLoadHandler(handler, browser.cefBrowser)
        toolWindow.component.add(browser.component)
    }

}