package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.RequestManager
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery
import com.intellij.ui.jcef.JBCefJSQuery.Response as JResponse

class CodeFactory(val browser: JBCefBrowser) {

    private var code = ""

    private fun makeFunction(query: JBCefJSQuery): String {
        val onSuccessCallback = "function(response) {window.bridge.call(response)}"
        val onFailureCallback = "function(code, message) {window.bridge.error(code, message)}"
        val code = query.inject("text", onSuccessCallback, onFailureCallback)
        return "function(text) {$code}"
    }

    fun make() {
        val query = JBCefJSQuery.create(browser)
        query.addHandler { text ->
            val request = Request.parse(text)
            println("call Java (${request.action}, ${request.key})")
            val json = RequestManager.handle(request).toJSON()
            JResponse(json)
        }
        val function = makeFunction(query)
        code = "window.JavaBridge = {call : $function};"
    }

    fun inject() {
        println(code)
        browser.cefBrowser.executeJavaScript(code, browser.cefBrowser.url, 0)
    }

}
