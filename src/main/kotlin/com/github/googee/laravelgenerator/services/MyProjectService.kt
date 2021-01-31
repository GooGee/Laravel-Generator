package com.github.googee.laravelgenerator.services

import com.intellij.openapi.project.Project
import com.github.googee.laravelgenerator.MyBundle
import com.github.googee.laravelgenerator.services.bridge.CodeFactory
import com.github.googee.laravelgenerator.services.bridge.Load
import com.github.googee.laravelgenerator.services.bridge.ToJS
import com.github.googee.laravelgenerator.services.bridge.Update
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.BrowserFactory

class MyProjectService(project: Project) {

    val browser = BrowserFactory.make()
    val toJS = ToJS(browser.cefBrowser)
    val codeFactory = CodeFactory(browser)
    val fm = FileManager(project)
    val load = Load(fm, toJS)
    val update = Update(fm, toJS)

    companion object {

        private var _instance: MyProjectService? = null

        val instance: MyProjectService
            get() = _instance as MyProjectService
    }

    init {
        println(MyBundle.message("projectService", project.name))
        _instance = this
    }
}
