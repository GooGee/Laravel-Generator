package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefJSQuery
import org.json.simple.JSONObject

class QueryManager {
    companion object {

        private fun getFunction(name: String, query: JBCefJSQuery): String {
            val onSuccessCallback = getSuccessCallback(name)
            val onFailureCallback = "function(error_code, error_message) {alert(error_message)}"
            return name + " : function(text) {" + query.inject("text", onSuccessCallback, onFailureCallback) + "}"
        }

        private fun getSuccessCallback(name: String): String {
            return "function(response) {window.bridge." + name + "CB(response)}"
        }

        fun register(browser: JBCefBrowser, project: Project): String {
            val fm = FileManager(project)
            val text = load(browser, fm) + "," + save(browser, fm) + "," + make(browser, fm) + "," + readDB(browser, fm)
            return "window.JavaBridge = {$text};"
        }

        private fun make(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                fm.make(text)
                null
            }
            return getFunction("make", query)
        }

        private fun readDB(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { uri ->
                var data = ""
                Request.get(uri) { text ->
                    data = text
                }
                JBCefJSQuery.Response(data)
            }
            return getFunction("readDB", query)
        }

        private fun load(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                val data = fm.load()
                JBCefJSQuery.Response(data)
            }
            return getFunction("load", query)
        }

        private fun save(browser: JBCefBrowser, fm: FileManager): String {
            val query = JBCefJSQuery.create(browser)
            query.addHandler { text ->
                fm.save(text)
                null
            }
            return getFunction("save", query)
        }

    }
}