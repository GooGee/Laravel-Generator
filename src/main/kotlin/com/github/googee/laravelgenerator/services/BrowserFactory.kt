package com.github.googee.laravelgenerator.services

import com.intellij.ui.jcef.JBCefBrowser

class BrowserFactory {
    companion object {
        val uri = System.getenv("CodeGeneratorURI")

        fun make(): JBCefBrowser {
            val browser = JBCefBrowser(uri)
            return browser
        }
    }
}