package br.com.etecmatao.theorycsharp.model

import java.util.*

data class Chapter(
    var id: String = UUID.randomUUID().toString(),
    var title: String,
    var text: String,
    var images: List<String>
)