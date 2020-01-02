package com.kfadli.deliveryman.model

import tornadofx.*

data class User(val username: String? = null, val password: String? = null)

class UserModel : ItemViewModel<User>() {
    val username = bind { item?.username?.toProperty() }
    val password = bind { item?.password?.toProperty() }
}