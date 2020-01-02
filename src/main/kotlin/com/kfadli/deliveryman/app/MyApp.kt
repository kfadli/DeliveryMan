package com.kfadli.deliveryman.app

import com.kfadli.deliveryman.view.MainView
import javafx.stage.Stage
import tornadofx.*

class MyApp : App(MainView::class, Styles::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.apply {
            width = 800.0
            height = 600.0
        }
    }
}