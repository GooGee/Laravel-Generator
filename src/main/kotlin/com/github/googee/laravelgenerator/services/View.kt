package com.github.googee.laravelgenerator.services

import com.intellij.ui.jcef.JBCefBrowser
import java.awt.Color
import java.awt.Component
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class View(val fm: FileManager) : JPanel() {
    private val browser: JBCefBrowser = BrowserFactory.make()
    private var code: String = ""
    private val label: JLabel

    init {
        this.background = Color.white
        this.layout = BoxLayout(this, BoxLayout.PAGE_AXIS)

        label = JLabel(makeIcon())
        label.foreground = Color.RED
        label.alignmentX = Component.CENTER_ALIGNMENT
        this.add(label)
    }

    fun addBrowser() {
        println("add browser")
        this.add(browser.component)
        code = QueryManager.register(browser, fm)
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

        val data = fm.load().toJSON()
        cefBrowser.executeJavaScript("window.bridge.load($data)", cefBrowser.url, 0)
    }

}
