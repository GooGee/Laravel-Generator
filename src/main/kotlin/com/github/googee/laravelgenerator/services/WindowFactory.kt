package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.jcef.JBCefApp

class WindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        if (!JBCefApp.isSupported()) {
            return
        }

        println("create browser")
        val browser = BrowserFactory.make()
        val code = QueryManager.register(browser, project)
        val handler = JCEFLoadHandler(browser, browser.cefBrowser.url, code)
        browser.jbCefClient.addLoadHandler(handler, browser.cefBrowser)
        toolWindow.component.add(browser.component)
    }

}