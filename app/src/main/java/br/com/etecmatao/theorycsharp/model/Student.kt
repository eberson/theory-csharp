package br.com.etecmatao.theorycsharp.model

import java.util.*

data class Student(
    var id: String = UUID.randomUUID().toString(),
    var firstname: String,
    var surename: String,
    var email: String,
    var phone: String,
    var picture: String,
    var credential: Credential,
    var subscriptions: List<Subscription> = mutableListOf()
){
    constructor(): this(
        firstname = "",
        surename = "",
        email = "",
        phone = "",
        picture = "",
        credential = Credential()
    )

    fun completeName(): String {
        return "$firstname $surename"
    }
}