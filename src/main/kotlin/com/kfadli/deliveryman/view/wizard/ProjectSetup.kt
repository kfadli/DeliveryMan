package com.kfadli.deliveryman.view.wizard

import com.kfadli.deliveryman.LOCAL_REPOSITORY_KEY
import com.kfadli.deliveryman.PREFERENCES_NAME
import com.kfadli.deliveryman.utils.GitHelper
import com.kfadli.deliveryman.view.MainView
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.stage.DirectoryChooser
import org.eclipse.jgit.api.Git
import tornadofx.*

class ProjectSetup : View("Import Project") {

    private var git: String = "https://github.com/kfadli/AlbumCoverDemo.git"
    private var artifactory: String = ""

    override val root = form {
        fieldset(title) {
            vbox(alignment = Pos.CENTER) {
                hboxConstraints {
                    this.hGrow = Priority.ALWAYS
                    this.marginBottom = 100.0
                }
                field("Git") {
                    textfield(git) {
                        minWidth = 200.0
                    }
                }
                button("Import") {
                    minWidth = 100.0

                    action {
                        import()
                    }
                }
            }
        }
    }

    private fun import() {

        var sourceDirectory = ""

        preferences(PREFERENCES_NAME) {
            sourceDirectory = get(LOCAL_REPOSITORY_KEY, "")
        }

        if (git.isEmpty()) {
            log.warning("Failed to import project, Git url is empty")
            return
        }

        /*
        if (artifactory.isEmpty()) {
            log.warning("Failed to import project, Artifactory url is empty")
            return
        }
        */

        if (sourceDirectory.isEmpty()) {
            log.warning("Failed to import project, source directory not setted")
            return
        }

        val git = GitHelper.checkout(git, sourceDirectory)

        git.use {
            replaceWith<MainView>()
        }

    }

}