package com.github.googee.laravelgenerator.services.request

import com.github.googee.laravelgenerator.services.bridge.Response
import com.github.googee.laravelgenerator.services.bridge.ToBrowser
import com.github.googee.laravelgenerator.services.view.EditorManager
import com.github.googee.laravelgenerator.services.json.JSRequest
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class EditRequest : Request() {

    fun register(browser: JBCefBrowser, tb: ToBrowser, em: EditorManager): String {
        val query = JBCefJSQuery.create(browser)
        val name = "edit"
        query.addHandler { text ->
            val json = JSRequest.load(text)
            em.show(json)
            tb.run(getHandlerName(name), Response.ok(json.key, "").toJSON())
            null
        }
        return makeFunction(name, query)
    }

}
