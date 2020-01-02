package com.kfadli.deliveryman.view.tabpane

import com.kfadli.deliveryman.model.Project
import tornadofx.*
import java.io.File

class DependenciesView : View("Dependencies") {

    override val root = vbox {

        button {
            text = "WestCoast"
        }
    }

    fun showDependencies(project: Project) {
        val result = File(project.path)
                .walk(FileWalkDirection.TOP_DOWN)
                .filter { file -> file.name == "build.gradle" }
                .filter { file -> file.readText().contains("implementation ") }

        result.forEach { println(it.absolutePath) }
    }
}
