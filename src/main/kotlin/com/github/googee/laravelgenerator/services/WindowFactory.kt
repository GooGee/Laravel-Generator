package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.Color
import java.awt.Component
import javax.swing.*

class WindowFactory : ToolWindowFactory {
    private var code: String = ""
    private var browser: JBCefBrowser? = null
    private val label = JLabel()
    private val panel = JPanel()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        makePanel()
        toolWindow.component.add(panel)
        if (!JBCefApp.isSupported()) {
            showError("Error: JCEF is required")
            return
        }

        println("create browser")
        val browser = BrowserFactory.make()
        this.browser = browser
        code = QueryManager.register(browser, project)
        val handler = JCEFLoadHandler(this)
        browser.jbCefClient.addLoadHandler(handler, browser.cefBrowser)
        panel.add(browser.component)
    }

    private fun makeIcon(): ImageIcon {
        val url = javaClass.getResource("/image/loading.gif")
        return ImageIcon(url)
    }

    private fun makePanel() {
        label.icon = makeIcon()
        label.foreground = Color.RED
        label.alignmentX = Component.CENTER_ALIGNMENT
        panel.layout = BoxLayout(panel, BoxLayout.PAGE_AXIS)
        panel.add(label)
    }

    fun showError(text: String) {
        println("showError")
        println(text)
        label.text = text
    }

    fun showWeb() {
        println("showWeb")
        panel.remove(0)
        panel.revalidate()
        val cefBrowser = browser!!.cefBrowser
        cefBrowser.executeJavaScript(code, cefBrowser.url, 0)
        cefBrowser.executeJavaScript("window.bridge.load()", cefBrowser.url, 0)
    }
}