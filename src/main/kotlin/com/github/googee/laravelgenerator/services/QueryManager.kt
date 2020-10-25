package com.github.googee.laravelgenerator.services

import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class QueryManager {
    companion object {

        fun register(browser: JBCefBrowser): String {
            val text = load(browser) + "," + save(browser) + "," + make(browser) + "," + readDB(browser)
            return "window.JavaBridge = {$text};"
        }

        fun make(browser: JBCefBrowser): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                FileManager.make(text)
                null
            }
            return "make : function(text) {" + query.inject("text") + "}"
        }

        fun readDB(browser: JBCefBrowser): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                val data = ""
                JBCefJSQuery.Response(data)
            }
            return "readDB : function() {" + query.inject("") + "}"
        }

        fun load(browser: JBCefBrowser): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                val data = FileManager.load()
                JBCefJSQuery.Response(data)
            }
            val onSuccessCallback = "function(response) {window.bridge.loadCB(response)}"
            val onFailureCallback = "function(error_code, error_message) {alert(error_message)}"
            return "load : function() {" + query.inject("", onSuccessCallback, onFailureCallback) + "}"
        }

        fun save(browser: JBCefBrowser): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                FileManager.save(text)
                null
            }
            return "save : function(text) {" + query.inject("text") + "}"
        }

    }
}