package com.kfadli.deliveryman.view

import com.kfadli.deliveryman.model.UserModel
import javafx.geometry.Orientation
import tornadofx.*

class LoginView : View("Login") {

    private val viewModel: LoginViewModel by inject()

    override val root = form {
        viewModel.validate(decorateErrors = false)
        fieldset(title, labelPosition = Orientation.VERTICAL) {
            field("Username") {
                textfield(viewModel.user.username).required()
            }
            field("Password") {
                passwordfield(viewModel.user.password).required()
            }
        }
        button("Login") {
            enableWhen(viewModel.user.valid)
            action { viewModel.login() }
        }
    }

}

class LoginViewModel : ViewModel() {

    val user: UserModel = UserModel()

    fun login() {
        println("onLogin")
    }
}