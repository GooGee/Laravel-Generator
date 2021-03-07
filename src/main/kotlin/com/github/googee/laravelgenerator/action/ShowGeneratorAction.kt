package com.github.googee.laravelgenerator.action

import com.github.googee.laravelgenerator.services.view.GeneratorViewFactory
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ShowGeneratorAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        if (e.project == null) {
            return
        }
        GeneratorViewFactory.show(e.project!!)
    }
}
