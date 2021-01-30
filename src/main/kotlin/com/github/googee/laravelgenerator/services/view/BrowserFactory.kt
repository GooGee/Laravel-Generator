package com.github.googee.laravelgenerator.services.view

import com.github.googee.laravelgenerator.services.http.Site
import com.intellij.ui.jcef.JBCefBrowser

class BrowserFactory {
    companion object {
        fun make(): JBCefBrowser {
            val uri = Site.getURI()
            println("URI: $uri")
            return JBCefBrowser(uri)
        }
    }
}
