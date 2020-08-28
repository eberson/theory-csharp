package br.com.etecmatao.theorycsharp.model

import java.time.LocalDate

data class Subscription(
    val student: Student,
    val subject: Subject,
    val created: LocalDate,
    val exercisesDone: List<ExerciseDone>
)