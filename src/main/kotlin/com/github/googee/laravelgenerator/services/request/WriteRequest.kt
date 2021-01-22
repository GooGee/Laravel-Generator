package com.github.googee.laravelgenerator.services.request

import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.json.JSRequest
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class WriteRequest : Request() {

    fun register(browser: JBCefBrowser, tb: ToBrowser, fm: FileManager): String {
        val query = JBCefJSQuery.create(browser)
        val name = "write"
        query.addHandler { text ->
            val json = JSRequest.load(text)
            val file = fm.getFullPath(json.key)
            FileManager.write(file, json.data)
            tb.run(getHandlerName(name), Response.ok(json.key, "").toJSON())
            null
        }
        return makeFunction(name, query)
    }

}
