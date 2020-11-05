package com.github.googee.laravelgenerator.services.bridge

import com.github.googee.laravelgenerator.services.EditorManager
import com.github.googee.laravelgenerator.services.file.FileManager
import com.intellij.ui.jcef.JBCefBrowser

class RequestManager {
    private val list = ArrayList<String>()

    fun register(browser: JBCefBrowser, tb: ToBrowser, em: EditorManager, fm: FileManager): String {
        list.add(EditRequest().register(browser, tb, em))
        list.add(HTTPRequest().register(browser, tb))
        list.add(HTTPRequest().register(browser, tb, true))
        list.add(ReadRequest().register(browser, tb, fm))
        list.add(WriteRequest().register(browser, tb, fm))
        val text = list.joinToString()
        return "window.JavaBridge = {$text};"
    }

}
