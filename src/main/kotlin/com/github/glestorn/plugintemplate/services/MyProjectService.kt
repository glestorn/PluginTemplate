package com.github.glestorn.plugintemplate.services

import com.github.glestorn.plugintemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
