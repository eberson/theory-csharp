package br.com.etecmatao.theorycsharp.model

import java.time.LocalDate

data class ExerciseDone(
    val exercise: Exercise,
    val answer: Answer,
    val created: LocalDate
)