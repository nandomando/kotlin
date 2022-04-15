package com.example.mytestapp.model


data class FireBaseItem(
    var id: String? = null,

    var userId: String? = null,

    var name: String? = null,

    var description: String? = null,

    var price: String? = null,

    var send: Boolean = false
) {}
