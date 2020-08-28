package br.com.etecmatao.theorycsharp.model

data class Credential(
    var email: String,
    var password: String
){
    constructor(): this(email = "", password = "")
}