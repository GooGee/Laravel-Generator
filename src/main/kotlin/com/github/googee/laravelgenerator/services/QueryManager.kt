package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery

class QueryManager {
    companion object {

        fun register(browser: JBCefBrowser, project: Project): String {
            val fm = FileManager(project)
            val text = load(browser, fm) + "," + save(browser, fm) + "," + make(browser, fm) + "," + readDB(browser, fm)
            return "window.JavaBridge = {$text};"
        }

        fun make(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                fm.make(text)
                null
            }
            return "make : function(text) {" + query.inject("text") + "}"
        }

        fun readDB(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                val data = ""
                JBCefJSQuery.Response(data)
            }
            return "readDB : function() {" + query.inject("") + "}"
        }

        fun load(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                val data = fm.load()
                JBCefJSQuery.Response(data)
            }
            val onSuccessCallback = "function(response) {window.bridge.loadCB(response)}"
            val onFailureCallback = "function(error_code, error_message) {alert(error_message)}"
            return "load : function() {" + query.inject("", onSuccessCallback, onFailureCallback) + "}"
        }

        fun save(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                fm.save(text)
                null
            }
            return "save : function(text) {" + query.inject("text") + "}"
        }

    }
}