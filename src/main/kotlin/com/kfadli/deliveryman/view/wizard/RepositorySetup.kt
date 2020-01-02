package com.kfadli.deliveryman.view.wizard

import com.kfadli.deliveryman.LOCAL_REPOSITORY_KEY
import com.kfadli.deliveryman.PREFERENCES_NAME
import com.kfadli.deliveryman.view.MainView
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.stage.DirectoryChooser
import tornadofx.*

class RepositorySetup : View("Local Repository") {

    private var path: String = ""

    override val root = form {
        fieldset(title) {
            hbox(alignment = Pos.CENTER) {
                hboxConstraints {
                    this.hGrow = Priority.ALWAYS
                    this.marginBottom = 100.0
                }
                field("Directory") {
                    textfield(path) {
                        minWidth = 200.0
                    }
                }
                button("Browse") {
                    action {
                        DirectoryChooser()
                                .apply { }
                                .also {
                                    path = it.showDialog(FX.primaryStage).absolutePath
                                }
                    }
                }
            }
            button(text = "Apply") {
                minWidth = 200.0

                action {
                    save()
                }
            }
        }
    }

    private fun save() {

        log.info("[save] LocalRepository: $path")

        preferences(PREFERENCES_NAME) {
            put(LOCAL_REPOSITORY_KEY, path)
        }

        replaceWith<MainView>()
    }
}