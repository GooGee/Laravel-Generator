package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.github.googee.laravelgenerator.MyBundle
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.GeneratorView

class MyProjectService(val project: Project) {

    companion object {

        private var _instance: MyProjectService? = null

        val instance: MyProjectService
            get() = _instance as MyProjectService
    }

    private var _view: GeneratorView? = null
    val view: GeneratorView
        get() {
            if (_view == null) {
                _view = Start.run(project)
            }
            return _view as GeneratorView
        }

    init {
        println(MyBundle.message("projectService", project.name))
        _instance = this
    }
}
