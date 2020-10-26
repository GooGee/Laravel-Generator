package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.layout.panel
import com.intellij.util.ui.UIUtil

class WindowFactory : ToolWindowFactory {
    var code: String = ""
    var browser: JBCefBrowser? = null
    var window: ToolWindow? = null

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        if (!JBCefApp.isSupported()) {
            addLabel("JCEF is not supported")
            return
        }

        println("create browser")
        window = toolWindow
        this.browser = BrowserFactory.make()
        val browser = this.browser!!
        code = QueryManager.register(browser, project)
        val handler = JCEFLoadHandler(this)
        browser.jbCefClient.addLoadHandler(handler, browser.cefBrowser)
        toolWindow.component.add(browser.component)
    }

    fun addLabel(text: String) {
        val panel = panel() {
            row {
                label(text, UIUtil.ComponentStyle.LARGE)
            }
        }
        window!!.component.add(panel)
    }

    fun showError(text: String) {
        println("showError")
        addLabel(text)
    }

    fun showWeb() {
        println("showWeb")
        val cefBrowser = browser!!.cefBrowser
        cefBrowser.executeJavaScript(code, cefBrowser.url, 0)
        cefBrowser.executeJavaScript("window.bridge.load()", cefBrowser.url, 0)
    }
}