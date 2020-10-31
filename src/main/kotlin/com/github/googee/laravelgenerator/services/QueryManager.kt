package com.github.googee.laravelgenerator.services

import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class QueryManager {
    companion object {

        private fun makeFunction(name: String, query: JBCefJSQuery): String {
            val onSuccessCallback = "function(response) {window.bridge.${name}Handler(response)}"
            val onFailureCallback = "function(error_code, error_message) {window.bridge.handleError(error_message)}"
            val text = query.inject("text", onSuccessCallback, onFailureCallback)
            return "$name : function(text) {$text}"
        }

        fun register(browser: JBCefBrowser, fm: FileManager): String {
            val text = edit(browser) + "," + read(browser, fm) + "," + write(browser, fm) + "," + get(browser) + "," + post(browser)
            return "window.JavaBridge = {$text};"
        }

        private fun edit(browser: JBCefBrowser): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                println(text)
                null
            }
            return makeFunction("edit", query)
        }

        private fun get(browser: JBCefBrowser): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                JBCefJSQuery.Response(RequestManager.get(text).toJSON())
            }
            return makeFunction("get", query)
        }

        private fun post(browser: JBCefBrowser): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                JBCefJSQuery.Response(RequestManager.post(text).toJSON())
            }
            return makeFunction("post", query)
        }

        private fun read(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                JBCefJSQuery.Response(fm.read(text).toJSON())
            }
            return makeFunction("read", query)
        }

        private fun write(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                JBCefJSQuery.Response(fm.write(text).toJSON())
            }
            return makeFunction("write", query)
        }

    }
}