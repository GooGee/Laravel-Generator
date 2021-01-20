package com.github.googee.laravelgenerator.services.request

import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.file.FileManager
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class RefreshRequest : Request() {

    fun register(browser: JBCefBrowser, tb: ToBrowser, fm: FileManager): String {
        val query = JBCefJSQuery.create(browser)
        val name = "refresh"
        query.addHandler { text ->
            FileManager.refresh()
            tb.run(getHandlerName(name), Response.ok("", "").toJSON())
            null
        }
        return makeFunction(name, query)
    }

}
