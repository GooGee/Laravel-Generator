package com.github.googee.laravelgenerator.services.bridge

interface IAction {

    fun run(request: Request): Response

}
