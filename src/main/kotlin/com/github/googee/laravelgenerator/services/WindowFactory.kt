package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.bridge.CodeFactory
import com.github.googee.laravelgenerator.services.bridge.Load
import com.github.googee.laravelgenerator.services.bridge.ToJS
import com.github.googee.laravelgenerator.services.bridge.Update
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.BrowserFactory
import com.github.googee.laravelgenerator.services.view.EditorManager
import com.github.googee.laravelgenerator.services.view.WebTab
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class WindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val browser = BrowserFactory.make()
        val toJS = ToJS(browser.cefBrowser)
        val update = Update()
        val em = EditorManager(project, toolWindow.contentManager, update)
        val fm = FileManager(project)
        RequestManager.register(em, fm)
        val fromJS = CodeFactory(browser)
        val load = Load(fm, toJS)
        val panel = WebTab(browser, load, fromJS)
        val tab = toolWindow.contentManager.factory.createContent(panel, "Generator", false)
        tab.isCloseable = false
        toolWindow.contentManager.addContent(tab)
    }

}
