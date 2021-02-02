package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.view.EditorManager
import com.github.googee.laravelgenerator.services.view.WebTab
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class WindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val service = MyProjectService.instance
        val em = EditorManager(project, toolWindow.contentManager, service.update)
        RequestManager.register(em, service.fm)
        val panel = WebTab(service.browser, service.load, service.codeFactory, service.toJS)
        val tab = toolWindow.contentManager.factory.createContent(panel, "Generator", false)
        tab.isCloseable = false
        toolWindow.contentManager.addContent(tab)
    }

}
