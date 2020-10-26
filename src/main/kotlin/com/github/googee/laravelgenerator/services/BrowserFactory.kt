package com.github.googee.laravelgenerator.services

import com.intellij.ui.jcef.JBCefBrowser

class BrowserFactory {
    companion object {
        val uri = "http://localhost:8080/"

        fun make(): JBCefBrowser {
            val browser = JBCefBrowser(uri)
            return browser
        }
    }
}