package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.Color
import java.awt.Component
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class View : JPanel() {
    private val browser: JBCefBrowser = BrowserFactory.make()
    private var code: String = ""
    private val label: JLabel

    init {
        this.layout = BoxLayout(this, BoxLayout.PAGE_AXIS)

        label = JLabel(makeIcon())
        label.foreground = Color.RED
        label.alignmentX = Component.CENTER_ALIGNMENT
        this.add(label)
    }

    fun addBrowser(project: Project) {
        println("add browser")
        this.add(browser.component)
        code = QueryManager.register(browser, project)
        val handler = JCEFLoadHandler(this)
        browser.jbCefClient.addLoadHandler(handler, browser.cefBrowser)
    }

    private fun makeIcon(): ImageIcon {
        val url = javaClass.getResource("/image/loading.gif")
        return ImageIcon(url)
    }

    fun showError(text: String) {
        println("showError")
        println(text)
        label.text = text
    }

    fun showWeb() {
        println("showWeb")
        this.remove(0)
        this.revalidate()
        val cefBrowser = browser.cefBrowser
        cefBrowser.executeJavaScript(code, cefBrowser.url, 0)
        cefBrowser.executeJavaScript("window.bridge.load()", cefBrowser.url, 0)
    }

}