package com.github.googee.laravelgenerator.listeners

import com.github.googee.laravelgenerator.services.Constant
import com.github.googee.laravelgenerator.services.file.FileManager
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.ex.ToolWindowManagerListener

class WindowListener : ToolWindowManagerListener {
    private var active = false

    override fun stateChanged(toolWindowManager: ToolWindowManager) {
        super.stateChanged(toolWindowManager)

        val tw = toolWindowManager.getToolWindow(Constant.CG)
        if (tw == null) {
            return
        }

        if (tw.isActive) {
            active = true
        }

        if (tw.isVisible == false) {
            if (active) {
                FileManager.refresh()
            }
            active = false
        }

    }

}
