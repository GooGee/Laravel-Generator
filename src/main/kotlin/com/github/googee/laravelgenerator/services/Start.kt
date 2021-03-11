package com.github.googee.laravelgenerator.services

import com.github.googee.laravelgenerator.editor.GeneratorVirtualFile
import com.github.googee.laravelgenerator.services.bridge.CodeFactory
import com.github.googee.laravelgenerator.services.bridge.Load
import com.github.googee.laravelgenerator.services.bridge.Save
import com.github.googee.laravelgenerator.services.bridge.ToJS
import com.github.googee.laravelgenerator.services.bridge.Update
import com.github.googee.laravelgenerator.services.file.FileManager
import com.github.googee.laravelgenerator.services.view.BrowserFactory
import com.github.googee.laravelgenerator.services.view.GeneratorView
import com.github.googee.laravelgenerator.services.view.JCEFLoadHandler
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class Start private constructor() {

    companion object {

        fun listen(project: Project, save: Save) {
            val listener = object : FileEditorManagerListener {
                override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
                    super.fileClosed(source, file)
                    if (file is GeneratorVirtualFile) {
                        save.run()
                    }
                }

                override fun selectionChanged(event: FileEditorManagerEvent) {
                    super.selectionChanged(event)
                    if (event.oldFile is GeneratorVirtualFile) {
                        save.run()
                    }
                }
            }
            project.messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, listener)
        }

        fun run(project: Project): GeneratorView {

            val browser = BrowserFactory.make()
            val codeFactory = CodeFactory(browser)
            val fm = FileManager(project)
            val toJS = ToJS(browser.cefBrowser)
            val load = Load(fm, toJS)
            val save = Save(toJS, fm)
            val update = Update(toJS)

            listen(project, save)

            RequestManager.register(fm, update, project)
            val view = GeneratorView(browser)
            val handler = JCEFLoadHandler(view, codeFactory, load)
            browser.jbCefClient.addLoadHandler(handler, browser.cefBrowser)
            return view
        }
    }
}
