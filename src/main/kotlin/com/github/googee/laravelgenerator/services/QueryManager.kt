package com.github.googee.laravelgenerator.services

import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class QueryManager {

    companion object {

        private fun getHandlerName(name: String): String {
            return "${name}Handler"
        }

        private fun makeFunction(name: String, query: JBCefJSQuery): String {
            val onSuccessCallback = "function(response) {window.bridge.handle(response)}"
            val onFailureCallback = "function(error_code, error_message) {window.bridge.handleError(error_code, error_message)}"
            val text = query.inject("text", onSuccessCallback, onFailureCallback)
            return "$name : function(text) {$text}"
        }

        fun register(browser: JBCefBrowser, em: EditorManager, fm: FileManager, tb: ToBrowser): String {
            val text = edit(browser, em, tb) + "," + read(browser, fm, tb) + "," + write(browser, fm, tb) + "," + get(browser, tb) + "," + post(browser, tb)
            return "window.JavaBridge = {$text};"
        }

        private fun edit(browser: JBCefBrowser, em: EditorManager, tb: ToBrowser): String {
            val query = JBCefJSQuery.create(browser)
            val name = "edit"
            query.addHandler { text ->
                tb.run(getHandlerName(name), em.show(text).toJSON())
                null
            }
            return makeFunction(name, query)
        }

        private fun get(browser: JBCefBrowser, tb: ToBrowser): String {
            val query = JBCefJSQuery.create(browser)
            val name = "get"
            query.addHandler { text ->
                tb.run(getHandlerName(name), RequestManager.get(text).toJSON())
                null
            }
            return makeFunction(name, query)
        }

        private fun post(browser: JBCefBrowser, tb: ToBrowser): String {
            val query = JBCefJSQuery.create(browser)
            val name = "post"
            query.addHandler { text ->
                tb.run(getHandlerName(name), RequestManager.post(text).toJSON())
                null
            }
            return makeFunction(name, query)
        }

        private fun read(browser: JBCefBrowser, fm: FileManager, tb: ToBrowser): String {
            val query = JBCefJSQuery.create(browser)
            val name = "read"
            query.addHandler { text ->
                tb.run(getHandlerName(name), fm.read(text).toJSON())
                null
            }
            return makeFunction(name, query)
        }

        private fun write(browser: JBCefBrowser, fm: FileManager, tb: ToBrowser): String {
            val query = JBCefJSQuery.create(browser)
            val name = "write"
            query.addHandler { text ->
                tb.run(getHandlerName(name), fm.write(text).toJSON())
                null
            }
            return makeFunction(name, query)
        }

    }

}
