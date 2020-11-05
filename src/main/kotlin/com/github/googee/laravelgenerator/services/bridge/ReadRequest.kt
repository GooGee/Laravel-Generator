package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.json.JSRequest
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class ReadRequest : Request() {

    fun register(browser: JBCefBrowser, tb: ToBrowser, fm: FileManager): String {
        val query = JBCefJSQuery.create(browser)
        val name = "read"
        query.addHandler { text ->
            val json = JSRequest.load(text)
            val file = fm.getFile(json.key)
            val content = FileManager.read(file)
            tb.run(getHandlerName(name), Response.ok(json.key, content).toJSON())
            null
        }
        return makeFunction(name, query)
    }

}
