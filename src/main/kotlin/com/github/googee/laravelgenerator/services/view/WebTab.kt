package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.bridge.CodeFactory
import com.github.googee.laravelgenerator.services.bridge.Load
import com.github.googee.laravelgenerator.services.bridge.ToJS
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.Color
import java.awt.Component
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class WebTab(private val browser: JBCefBrowser, private val load: Load, val codeFactory: CodeFactory, val toJS: ToJS) :
    JPanel() {
    private val label: JLabel

    init {
        this.background = Color.white
        this.layout = BoxLayout(this, BoxLayout.PAGE_AXIS)

        label = JLabel(makeIcon())
        label.foreground = Color.RED
        label.alignmentX = Component.CENTER_ALIGNMENT
        this.add(label)

        check()
    }

    private fun check() {
        if (!JBCefApp.isSupported()) {
            showError("Error: JCEF is required")
            return
        }

        addBrowser()
    }

    private fun addBrowser() {
        this.add(browser.component)
        codeFactory.make()
        val handler = JCEFLoadHandler(this)
        browser.jbCefClient.addLoadHandler(handler, browser.cefBrowser)
    }

    private fun makeIcon(): ImageIcon {
        val url = javaClass.getResource("/image/loading.gif")
        return ImageIcon(url)
    }

    fun showError(text: String) {
        println(text)
        label.text = text
    }

    fun showWeb() {
        this.remove(0)
        this.revalidate()
        codeFactory.inject()
        toJS.ready = true
        load.run()
    }

}
