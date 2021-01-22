package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.bridge.FromJS
import com.github.googee.laravelgenerator.services.bridge.Load
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.Color
import java.awt.Component
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class WebTab(private val browser: JBCefBrowser, private val load: Load, val fromJS: FromJS) : JPanel() {
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
        println("add browser")
        this.add(browser.component)
        fromJS.makeCode()
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
        fromJS.inject()
        load.run()
    }

}
