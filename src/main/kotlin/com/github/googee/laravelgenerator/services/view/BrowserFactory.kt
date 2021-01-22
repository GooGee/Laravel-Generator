package com.github.googee.laravelgenerator.services.view

import com.intellij.ui.jcef.JBCefBrowser
import java.util.*

class BrowserFactory {
    companion object {
        const val FileName = "/data.properties"
        const val WebKey = "WebURI"

        private fun getURI(): String {
            val uri = System.getenv(WebKey)
            if (uri.isNullOrEmpty()) {
                val properties = Properties()
                properties.load(object {}.javaClass.getResourceAsStream(FileName))
                return properties.get(WebKey) as String
            }
            return uri
        }

        fun make(): JBCefBrowser {
            val uri = getURI()
            println("WebURI: $uri")
            return JBCefBrowser(uri)
        }
    }
}
