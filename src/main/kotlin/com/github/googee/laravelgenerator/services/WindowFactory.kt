package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.services.view.WebTab
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class WindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val manager = toolWindow.contentManager
        val em = EditorManager(manager)
        val fm = FileManager(project)
        val panel = WebTab(em, fm)
        val tab = manager.factory.createContent(panel, "Generator", false)
        tab.isCloseable = false
        manager.addContent(tab)
    }

}
