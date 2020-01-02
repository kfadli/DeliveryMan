package com.kfadli.deliveryman.view

import com.kfadli.deliveryman.LOCAL_REPOSITORY_KEY
import com.kfadli.deliveryman.PREFERENCES_NAME
import com.kfadli.deliveryman.model.Project
import com.kfadli.deliveryman.view.tabpane.DependenciesView
import com.kfadli.deliveryman.view.wizard.RepositorySetup
import com.kfadli.deliveryman.view.wizard.ProjectSetup
import javafx.scene.control.TabPane
import tornadofx.*
import java.io.File

class MainView : View("DeliveryMan") {

    private val viewModel: RepositoryViewModel by inject()

    private val dependencies: DependenciesView by inject()

    private val listview = listview<Project> {
        maxWidth = 150.0
        cellFormat {
            graphic = cache {
                form {
                    label(it.label)
                }
            }
        }

        onUserSelect(clickCount = 1) {
            dependencies.showDependencies(it)
        }
    }

    private val details =  tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab("Informations", "informations") {}
        tab("Git", "git") {}
    }

    override val root = hbox {}

    override fun onBeforeShow() {
        if (viewModel.sourceDirectory.isEmpty()) {
            replaceWith<RepositorySetup>()
            return
        }

        if (viewModel.repositories.isEmpty()) {
            replaceWith<ProjectSetup>()
            return
        }

        listview.items = viewModel.repositories.observable()
    }

    init {
        viewModel.load()

        root.add(listview)
        root.add(details)

        log.info("Projects: ${viewModel.repositories}")
        root.children[1].add(dependencies)
    }
}

class RepositoryViewModel : ViewModel() {

    var repositories = mutableListOf<Project>()
    var sourceDirectory = ""

    fun load() {

        preferences(PREFERENCES_NAME) {
            sourceDirectory = get(LOCAL_REPOSITORY_KEY, "")
        }

        File(sourceDirectory).listFiles()?.let { files ->
            files.filter { it.isDirectory }
                .map { Project(it.name, "", it.absolutePath) }
                .forEach { repositories.add(it) }
        }

    }
}