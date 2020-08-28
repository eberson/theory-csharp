package br.com.etecmatao.theorycsharp.model

data class Subject (
    var id: Long?,
    var title: String,
    var description: String,
    var image: String,
    var chapters: List<Chapter>,
    var exercises: List<Exercise>
)